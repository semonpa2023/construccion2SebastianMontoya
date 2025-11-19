package app.infrastructure.gui;

import app.domain.entities.Admin;
import app.domain.services.AdminService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

/**
 * Interfaz gráfica para la gestión de administradores utilizando JOptionPane.
 * Permite registrar, listar, buscar y eliminar administradores desde menús interactivos.
 */
@Component
public class AdminGUI {

    private final AdminService adminService;

    public AdminGUI(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Muestra el menú principal de administración con opciones para gestionar administradores.
     */
    public void showMenu() {
        String[] options = {"Registrar admin", "Listar admins", "Buscar admin", "Eliminar admin", "Volver"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Menú Administrador:",
                    "Gestión de Administradores",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice < 0 || choice == 4) return;
            switch (choice) {
                case 0 -> registerAdmin();
                case 1 -> listAdmins();
                case 2 -> searchAdmin();
                case 3 -> deleteAdmin();
            }
        }
    }

    /**
     * Permite registrar un nuevo administrador solicitando los datos por JOptionPane.
     */
    private void registerAdmin() {
        try {
            String fullName = JOptionPane.showInputDialog("Ingrese el nombre completo:");
            if (fullName == null || fullName.isBlank()) return;

            String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
            if (username == null || username.isBlank()) return;

            String password = JOptionPane.showInputDialog("Ingrese la contraseña:");
            if (password == null || password.isBlank()) return;

            adminService.createAdmin(new Admin(fullName, username, password));
            JOptionPane.showMessageDialog(null, "Administrador registrado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar administrador: " + e.getMessage());
        }
    }

    /**
     * Muestra la lista de administradores registrados en un cuadro de diálogo.
     */
    private void listAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        if (admins.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay administradores registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("Lista de administradores:\n");
        for (Admin a : admins) {
            sb.append(a.getId())
              .append(" - ")
              .append(a.getFullName())
              .append(" (")
              .append(a.getUsername())
              .append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    /**
     * Permite buscar un administrador por su nombre de usuario y muestra la información encontrada.
     */
    private void searchAdmin() {
        String username = JOptionPane.showInputDialog("Ingrese el username a buscar:");
        if (username == null || username.isBlank()) return;

        Admin admin = adminService.findByUsername(username);
        if (admin == null)
            JOptionPane.showMessageDialog(null, "No se encontró un administrador con ese username.");
        else
            JOptionPane.showMessageDialog(null, "Administrador encontrado:\n" +
                    "Nombre: " + admin.getFullName() + "\nUsuario: " + admin.getUsername());
    }

    /**
     * Permite eliminar un administrador por su ID solicitando el dato por JOptionPane.
     */
    private void deleteAdmin() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del administrador a eliminar:");
        if (idStr == null || idStr.isBlank()) return;

        try {
            Long id = Long.parseLong(idStr);
            adminService.deleteAdmin(id);
            JOptionPane.showMessageDialog(null, "Administrador eliminado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar administrador: " + e.getMessage());
        }
    }
}
