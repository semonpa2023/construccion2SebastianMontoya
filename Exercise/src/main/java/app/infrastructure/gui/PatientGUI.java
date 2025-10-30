package app.infrastructure.gui;

import app.domain.entities.Patient;
import app.domain.services.PatientService;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Interfaz gráfica (JOptionPane) para el registro y consulta de pacientes.
 */
@Component
public class PatientGUI {

    private final PatientService patientService;

    public PatientGUI(PatientService patientService) {
        this.patientService = patientService;
    }

    // Registrar paciente desde ventana emergente
    public void registerPatient() {
        try {
            String fullName = JOptionPane.showInputDialog("Ingrese el nombre completo del paciente:");
            if (fullName == null || fullName.isBlank()) return;

            String document = JOptionPane.showInputDialog("Ingrese el número de documento:");
            if (document == null || document.isBlank()) return;

            String birthDateInput = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento (YYYY-MM-DD):");
            if (birthDateInput == null) return;
            LocalDate birthDate = LocalDate.parse(birthDateInput);

            String address = JOptionPane.showInputDialog("Ingrese la dirección:");
            String phone = JOptionPane.showInputDialog("Ingrese el teléfono:");
            String email = JOptionPane.showInputDialog("Ingrese el correo electrónico:");
            String gender = JOptionPane.showInputDialog("Ingrese el sexo (M/F):");

            Patient patient = new Patient();
            patient.setFullName(fullName);
            patient.setDocumentNumber(document);
            patient.setBirthDate(birthDate);
            patient.setAddress(address);
            patient.setPhone(phone);
            patient.setEmail(email);
            patient.setGender(gender);

            patientService.registerPatient(patient);
            JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente.");

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }
}
