package app.infrastructure.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;

/*
 * Menú principal de la aplicación hospitalaria.
 * Permite al usuario seleccionar su rol y redirige al panel (GUI) correspondiente.
 * Todo el texto se muestra en español.
 * Este menú centraliza la navegación entre los distintos módulos de la aplicación.
 */
@Component
public class MainMenu {

    // GUIs de cada rol
    private final RHGUI rhGUI;
    private final DoctorGUI doctorGUI;
    private final NurseGUI nurseGUI;
    private final AdministrativeGUI administrativeGUI;
    private final SupportGUI supportGUI;
    private final AdminGUI adminGUI;

    /*
     * Constructor que inyecta todas las GUIs necesarias para cada rol.
     * Spring se encarga de la inyección de dependencias.
     */
    public MainMenu(
            RHGUI rhGUI,
            DoctorGUI doctorGUI,
            NurseGUI nurseGUI,
            AdministrativeGUI administrativeGUI,
            SupportGUI supportGUI,
            AdminGUI adminGUI
    ) {
        this.rhGUI = rhGUI;
        this.doctorGUI = doctorGUI;
        this.nurseGUI = nurseGUI;
        this.administrativeGUI = administrativeGUI;
        this.supportGUI = supportGUI;
        this.adminGUI = adminGUI;
    }

    /**
     * Muestra el menú principal.
     * 
     * Flujo:
     * 1. Se despliega un JOptionPane con los roles disponibles y la opción de salir.
     * 2. El usuario selecciona un rol:
     *      - Si selecciona "Salir" o cierra el diálogo, se termina la ejecución del menú.
     *      - Si selecciona un rol, se llama al método showMenu() correspondiente de esa GUI.
     * 3. El menú se repite hasta que el usuario elige salir.
     */
    public void showMainMenu() {
        String[] options = {
                "Recursos Humanos",
                "Médico",
                "Enfermera",
                "Administrativo",
                "Soporte",
                "Admin",
                "Salir"
        };

        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione su rol:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            // Validación de salida: opción negativa (cerrar ventana) o "Salir"
            if (choice < 0 || choice == 6) {
                JOptionPane.showMessageDialog(null, "👋 Saliendo del sistema...");
                return;
            }

            // Redirige a la GUI correspondiente según el rol
            switch (choice) {
                case 0 -> rhGUI.showMenu();              // Recursos Humanos
                case 1 -> doctorGUI.showMenu();          // Médico
                case 2 -> nurseGUI.showMenu();           // Enfermera
                case 3 -> administrativeGUI.showMenu(); // Personal Administrativo
                case 4 -> supportGUI.showMenu();         // Soporte de Información
                case 5 -> adminGUI.showMenu();           // Administrador
                default -> JOptionPane.showMessageDialog(null, "⚠️ Opción no válida.");
            }
        }
    }
}
