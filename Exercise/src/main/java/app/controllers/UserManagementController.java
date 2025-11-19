package app.controllers;

import app.domain.entities.User;
import app.domain.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserManagementController {

    private final UserService userService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // ---------- LISTAR USUARIOS (opcionalmente filtrando por rol) ----------
    @GetMapping
    public List<UserResponse> listUsers(@RequestParam(required = false) String role) {
        return userService.findAll()
                .stream()
                .filter(u -> role == null || role.equalsIgnoreCase(u.getRole()))
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // ---------- OBTENER UN USUARIO POR USERNAME ----------
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        Optional<User> userOpt = userService.findByUsername(username);
        return userOpt
                .map(user -> ResponseEntity.ok(UserResponse.fromEntity(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ---------- CREAR USUARIO ----------
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = new User();
            user.setFullName(request.getFullName());
            user.setDocumentNumber(request.getDocumentNumber());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setBirthDate(LocalDate.parse(request.getBirthDate(), formatter)); // dd/MM/yyyy
            user.setAddress(request.getAddress());
            user.setRole(request.getRole().toUpperCase());   // HR, ADMIN, DOCTOR...
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());         // se encripta en UserService

            String result = userService.saveUser(user);

            if (!result.startsWith("✅")) {
                return ResponseEntity.badRequest().body(result);
            }

            return ResponseEntity.ok(UserResponse.fromEntity(user));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear usuario: " + e.getMessage());
        }
    }

    // ---------- ACTUALIZAR USUARIO ----------
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(
            @PathVariable String username,
            @RequestBody UpdateUserRequest request) {

        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            user.setPhone(request.getPhone());
        }
        if (request.getAddress() != null && !request.getAddress().isBlank()) {
            user.setAddress(request.getAddress());
        }
        if (request.getRole() != null && !request.getRole().isBlank()) {
            user.setRole(request.getRole().toUpperCase());
        }

        String result = userService.updateUser(user);
        if (!result.startsWith("✅")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(UserResponse.fromEntity(user));
    }

    // ---------- ELIMINAR USUARIO ----------
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        boolean ok = userService.deleteByUsername(username);
        if (!ok) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

    // ================== DTOs ==================

    @Data
    public static class CreateUserRequest {
        private String fullName;
        private String documentNumber;
        private String email;
        private String phone;
        private String birthDate;   // dd/MM/yyyy
        private String address;
        private String role;        // HR, ADMIN, DOCTOR, NURSE...
        private String username;
        private String password;
    }

    @Data
    public static class UpdateUserRequest {
        private String email;
        private String phone;
        private String address;
        private String role;        // opcional
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String fullName;
        private String documentNumber;
        private String email;
        private String phone;
        private String birthDate;
        private String address;
        private String role;
        private String username;

        public static UserResponse fromEntity(User user) {
            UserResponse dto = new UserResponse();
            dto.setId(user.getId());
            dto.setFullName(user.getFullName());
            dto.setDocumentNumber(user.getDocumentNumber());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setBirthDate(user.getBirthDate() != null
                    ? user.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    : null);
            dto.setAddress(user.getAddress());
            dto.setRole(user.getRole());
            dto.setUsername(user.getUsername());
            return dto;
        }
    }
}
