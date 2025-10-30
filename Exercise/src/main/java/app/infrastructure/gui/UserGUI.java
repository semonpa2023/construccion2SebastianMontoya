package app.infrastructure.gui;

import app.domain.services.UserService;
import app.domain.entities.User;
import app.domain.enums.Role;
import app.infrastructure.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
  Interfaz para registro de usuario con JOptionPane.
  Código en inglés, comentarios en español.
*/
@Component
public class UserGUI implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RHGUI rhGUI;

    @Autowired
    private DoctorGUI doctorGUI;

    @Autowired
    private NurseGUI nurseGUI;

    @Autowired
    private AdministrativeGUI administrativeGUI;

    @Autowired
    private SupportGUI supportGUI;
    
    @Autowired
    private AdminGUI adminGUI;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void run(String... args) {
        // ✅ Ahora MainMenu recibe todas las GUIs necesarias
        MainMenu menu = new MainMenu(rhGUI, doctorGUI, nurseGUI, administrativeGUI, supportGUI, adminGUI);
        menu.showMainMenu();
    }

    public void registerUser() {
        try {
            String fullName = InputUtils.askRequired("Ingrese el nombre completo:");
            if (fullName == null) return;

            String documentNumber = InputUtils.askRequired("Ingrese la cédula:");
            if (documentNumber == null) return;
            if (!ValidatorUtil.isNumeric(documentNumber)) {
                JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
                return;
            }

            String email = InputUtils.askRequired("Ingrese el correo electrónico:");
            if (email == null) return;

            String phone = InputUtils.askRequired("Ingrese el número de teléfono:");
            if (phone == null) return;

            String birthStr = InputUtils.askRequired("Ingrese la fecha de nacimiento (DD/MM/YYYY):");
            if (birthStr == null) return;
            LocalDate birthDate;
            try {
                birthDate = LocalDate.parse(birthStr, formatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido.");
                return;
            }

            String address = InputUtils.askOptional("Ingrese la dirección:");
            if (address == null) return;

            String roleStr = InputUtils.askRequired("Ingrese el rol (HR, ADMIN, DOCTOR, NURSE, ADMINISTRATIVE, SUPPORT):");
            if (roleStr == null) return;
            Role role;
            try {
                role = Role.valueOf(roleStr.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Rol inválido.");
                return;
            }

            String username = InputUtils.askRequired("Ingrese el nombre de usuario:");
            if (username == null) return;

            String password = InputUtils.askRequired("Ingrese la contraseña:");
            if (password == null) return;

            User user = new User();
            user.setFullName(fullName);
            user.setDocumentNumber(documentNumber);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBirthDate(birthDate);
            user.setAddress(address);
            user.setRole(role.name());
            user.setUsername(username);
            user.setPassword(password);

            String result = userService.saveUser(user);
            JOptionPane.showMessageDialog(null, result);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
