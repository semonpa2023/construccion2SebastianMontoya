package app.controllers;

import app.domain.entities.User;
import app.domain.repositories.UserRepository;
import app.security.JwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository; // 👈 importante

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        // 1️⃣ Validar credenciales (si son malas, lanza excepción)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );

        // 2️⃣ Buscar el usuario en BD con tu repositorio
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3️⃣ Obtener el rol desde tu modelo (HR, DOCTOR, etc.)
        String appRole = user.getRole(); // ej: "HR"

        // 4️⃣ Generar el token. Puedes guardar el rol "limpio" o con prefijo, tú decides.
        String token = jwtUtil.generateToken(user.getUsername(), appRole);

        // 5️⃣ Respuesta al cliente
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", appRole);          // 👉 "HR", "DOCTOR", etc.
        response.put("username", user.getUsername());

        return response;
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }
}
