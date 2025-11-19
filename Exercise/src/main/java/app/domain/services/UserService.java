package app.domain.services;

import app.domain.entities.User;
import app.domain.repositories.UserRepository;
import app.infrastructure.util.ValidatorUtil;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
  Servicio que valida y gestiona usuarios.
*/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Validator validator;

    public UserService() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

   // ----------------- CREAR USUARIO -----------------
    public String saveUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> v : violations) {
                sb.append("- ").append(v.getMessage()).append("\n");
            }
            return sb.toString();
        }

        if (!user.isAgeValid()) return "Edad no válida (máximo 150 años).";
        if (!ValidatorUtil.isValidEmail(user.getEmail())) return "Correo inválido.";
        if (!ValidatorUtil.isValidPassword(user.getPassword())) return "Contraseña inválida (mínimo 8, 1 mayúscula, 1 número, 1 símbolo).";
        if (!ValidatorUtil.isValidPhone(user.getPhone())) return "Teléfono inválido (máx. 10 dígitos).";

        if (userRepository.existsByUsername(user.getUsername())) return "El usuario ya existe.";
        if (userRepository.existsByEmail(user.getEmail())) return "El correo ya está registrado.";
        if (userRepository.findByDocumentNumber(user.getDocumentNumber()).isPresent()) return "La cédula ya existe.";

        // DEBUG 1: ver contraseña en texto plano
        System.out.println("Password ANTES de encriptar: " + user.getPassword());

        // 🔐 Encriptar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // DEBUG 2: ver contraseña encriptada
        System.out.println("Password DESPUÉS de encriptar: " + user.getPassword());

        userRepository.save(user);
        return "✅ Usuario guardado correctamente.";
    }

    // ----------------- LISTAR TODOS -----------------
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // ----------------- BUSCAR POR USERNAME -----------------
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ----------------- ACTUALIZAR USUARIO -----------------
    public String updateUser(User user) {
        if (user == null) return "Usuario no válido.";

        // Validaciones básicas
        if (!ValidatorUtil.isValidEmail(user.getEmail())) return "Correo inválido.";
        if (!ValidatorUtil.isValidPhone(user.getPhone())) return "Teléfono inválido.";
        if (!user.isAgeValid()) return "Edad no válida (máximo 150 años).";

        // Si viene una nueva contraseña en texto plano, encriptarla
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
        return "✅ Usuario actualizado correctamente.";
    }

    // ----------------- ELIMINAR POR USERNAME -----------------
    public boolean deleteByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(u -> {
                    userRepository.delete(u);
                    return true;
                })
                .orElse(false);
    }

    // ----------------- VALIDAR CONTRASEÑA -----------------
    public static boolean isValidPassword(String password) {
        return ValidatorUtil.isValidPassword(password);
    }
}
