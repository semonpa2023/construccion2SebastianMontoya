package app.infrastructure.gui;

import app.domain.entities.Appointment;
import app.domain.enums.AppointmentStatus;
import app.domain.services.AppointmentService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Interfaz gráfica (JOptionPane) para el manejo de citas médicas.
 */
@Component
public class AppointmentGUI {

    private final AppointmentService appointmentService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AppointmentGUI(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Registrar una nueva cita médica.
     */
    public void registerAppointment() {
        try {
            String patientName = JOptionPane.showInputDialog("Ingrese el nombre completo del paciente:");
            if (patientName == null || patientName.isBlank()) return;

            String document = JOptionPane.showInputDialog("Ingrese el número de documento del paciente:");
            if (document == null || document.isBlank()) return;

            String doctor = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
            if (doctor == null || doctor.isBlank()) return;

            String specialty = JOptionPane.showInputDialog("Ingrese la especialidad:");
            if (specialty == null || specialty.isBlank()) return;

            String dateInput = JOptionPane.showInputDialog("Ingrese la fecha y hora de la cita (formato: yyyy-MM-dd HH:mm):");
            if (dateInput == null || dateInput.isBlank()) return;

            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);

            Appointment appointment = new Appointment();
            appointment.setPatientName(patientName);
            appointment.setPatientDocument(document);
            appointment.setDoctorName(doctor);
            appointment.setSpecialty(specialty);
            appointment.setAppointmentDate(date);

            appointmentService.registerAppointment(appointment);
            JOptionPane.showMessageDialog(null, "Cita médica registrada exitosamente.");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error de formato: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Lista todas las citas registradas.
     */
    public void listAppointments() {
        var appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay citas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("Citas médicas registradas:\n\n");
        appointments.forEach(a ->
            sb.append("ID: ").append(a.getId())
              .append(" | Paciente: ").append(a.getPatientName())
              .append(" | Médico: ").append(a.getDoctorName())
              .append(" | Fecha: ").append(a.getAppointmentDate().format(formatter))
              .append(" | Estado: ").append(a.getStatus())
              .append("\n")
        );
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    /**
     * Cambia el estado de una cita existente.
     */
    public void changeStatus() {
        try {
            String idInput = JOptionPane.showInputDialog("Ingrese el ID de la cita a actualizar:");
            if (idInput == null || idInput.isBlank()) return;

            Long id = Long.parseLong(idInput);

            String[] options = {"SCHEDULED", "COMPLETED", "CANCELED"};
            String selected = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el nuevo estado:",
                    "Actualizar Estado",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (selected == null) return;

            appointmentService.updateStatus(id, AppointmentStatus.valueOf(selected));
            JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
