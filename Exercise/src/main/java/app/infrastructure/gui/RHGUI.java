package app.infrastructure.gui;

import app.domain.entities.User;
import app.domain.services.UserService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/*
  Recursos Humanos GUI:
  - Crear usuario
  - Actualizar usuario
  - Listar usuarios
  - Eliminar por username
  Comentarios en español; código en inglés.
*/
@Component
public class RHGUI {

    private final UserService userService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RHGUI(UserService userService) {
        this.userService = userService;
    }

    public void showMenu() {
        String[] opts = {"Crear usuario", "Actualizar usuario", "Listar usuarios", "Eliminar usuario", "Volver"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Menú Recursos Humanos:", "RH",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);
            if (choice < 0 || choice == 4) return;
            switch (choice) {
                case 0 -> createUser();
                case 1 -> updateUser();    
                case 2 -> listUsers();
                case 3 -> deleteByUsername();
            }
        }
    }


    // -------------------- CREAR USUARIO --------------------
    private void createUser() {
        try {
            String fullName = InputUtils.askRequired("Ingrese el nombre completo:");
            if (fullName == null) return;

            String documentNumber = InputUtils.askRequired("Ingrese el número de cédula (solo números):");
            if (documentNumber == null) return;

            String email = InputUtils.askRequired("Ingrese el correo electrónico:");
            if (email == null) return;

            String phone = InputUtils.askRequired("Ingrese el teléfono (1-10 dígitos):");
            if (phone == null) return;

            String birth = InputUtils.askRequired("Ingrese la fecha de nacimiento (DD/MM/YYYY):");
            if (birth == null) return;

            String address = InputUtils.askOptional("Ingrese la dirección (máx 30 caracteres):");
            if (address == null) return;

            String role = InputUtils.askRequired("Ingrese el rol (HR, ADMIN, DOCTOR, NURSE, ADMINISTRATIVE, SUPPORT):");
            if (role == null) return;

            String username = InputUtils.askRequired("Ingrese el nombre de usuario (máx 15 chars):");
            if (username == null) return;

            // Intentos de contraseña sin reiniciar todo el formulario
            String password = null;
            int attempts = 0;
            while (attempts < 3) {
                password = InputUtils.askRequired("Ingrese la contraseña (mín 8, 1 mayúscula, 1 número, 1 símbolo):");
                if (password == null) return;
                if (UserService.isValidPassword(password)) break;
                JOptionPane.showMessageDialog(null, "❌ Contraseña inválida. Intente nuevamente.");
                attempts++;
            }
            if (attempts == 3) {
                JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos.");
                return;
            }

            User user = new User();
            user.setFullName(fullName);
            user.setDocumentNumber(documentNumber);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBirthDate(java.time.LocalDate.parse(birth, formatter));
            user.setAddress(address);
            user.setRole(role.toUpperCase());
            user.setUsername(username);
            user.setPassword(password);

            String result = userService.saveUser(user);
            JOptionPane.showMessageDialog(null, result);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear usuario: " + e.getMessage());
        }
    }

    // -------------------- ACTUALIZAR USUARIO --------------------
    private void updateUser() {
        String username = InputUtils.askRequired("Ingrese el nombre de usuario a actualizar:");
        if (username == null) return;

        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            return;
        }

        User user = userOpt.get();

        String newEmail = InputUtils.askOptional("Correo actual: " + user.getEmail() + "\nNuevo correo (Enter para mantener):");
        if (newEmail != null && !newEmail.isBlank()) user.setEmail(newEmail);

        String newPhone = InputUtils.askOptional("Teléfono actual: " + user.getPhone() + "\nNuevo teléfono (Enter para mantener):");
        if (newPhone != null && !newPhone.isBlank()) user.setPhone(newPhone);

        String newAddress = InputUtils.askOptional("Dirección actual: " + user.getAddress() + "\nNueva dirección (Enter para mantener):");
        if (newAddress != null && !newAddress.isBlank()) user.setAddress(newAddress);

        String result = userService.updateUser(user);
        JOptionPane.showMessageDialog(null, result);
    }

    // -------------------- LISTAR USUARIOS --------------------
    private void listUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        users.forEach(u -> sb.append("ID: ").append(u.getId())
                .append(" | Usuario: ").append(u.getUsername())
                .append(" | Nombre: ").append(u.getFullName())
                .append(" | Rol: ").append(u.getRole())
                .append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString(), "Usuarios", JOptionPane.INFORMATION_MESSAGE);
    }

    // -------------------- ELIMINAR USUARIO --------------------
    private void deleteByUsername() {
        String username = InputUtils.askRequired("Ingrese el nombre de usuario a eliminar:");
        if (username == null) return;
        boolean ok = userService.deleteByUsername(username);
        JOptionPane.showMessageDialog(null, ok ? "Usuario eliminado correctamente." : "Usuario no encontrado.");
    }
}
