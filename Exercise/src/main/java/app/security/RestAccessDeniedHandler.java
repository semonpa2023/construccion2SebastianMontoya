package app.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        response.setContentType("application/json;charset=UTF-8");

        String json = String.format(
                "{ \"status\": 403, \"error\": \"Forbidden\", \"message\": \"%s\", \"path\": \"%s\" }",
                accessDeniedException.getMessage(),
                request.getRequestURI()
        );

        response.getWriter().write(json);
    }
}
