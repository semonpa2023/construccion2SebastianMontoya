package app.security;

import app.security.JwtAuthenticationFilter;
import app.security.RestAccessDeniedHandler;
import app.security.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final RestAuthenticationEntryPoint authenticationEntryPoint; // 👈 nuevo
    private final RestAccessDeniedHandler accessDeniedHandler;           // 👈 nuevo

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/users/**").hasRole("HR")
                        .requestMatchers("/api/hr/**").hasRole("HR")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/api/nurse/**").hasRole("NURSE")
                        .requestMatchers("/api/administrative/**").hasRole("ADMINISTRATIVE")
                        .requestMatchers("/api/support/**").hasRole("SUPPORT")
                        .requestMatchers("/api/medications").hasAnyRole("SUPPORT","ADMIN")
                        .requestMatchers("/api/medications/**").hasAnyRole("SUPPORT","ADMIN")
                        .anyRequest().authenticated()
                )
                // 🔹 Aquí definimos cómo responder en 401 y 403
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint) // 401
                        .accessDeniedHandler(accessDeniedHandler)           // 403
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt para encriptar contraseñas
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
