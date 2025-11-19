package app.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                // No hay token → dejamos que Security decida (luego lanzará 401)
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);

            // ⚠️ Aquí puede saltar excepción si el token es inválido/expirado
            username = jwtUtil.extractUsername(jwt);

        } catch (ExpiredJwtException e) {
            // Token expirado → 401
            buildErrorResponse(response, 401, "Token expirado", request.getRequestURI());
            return;
        } catch (JwtException | IllegalArgumentException e) {
            // Token mal formado o firma inválida → 401
            buildErrorResponse(response, 401, "Token inválido", request.getRequestURI());
            return;
        }

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void buildErrorResponse(HttpServletResponse response,
                                    int status,
                                    String message,
                                    String path) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        String json = String.format(
                "{ \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\" }",
                status,
                status == 401 ? "Unauthorized" : "Error",
                message,
                path
        );
        response.getWriter().write(json);
    }
}
