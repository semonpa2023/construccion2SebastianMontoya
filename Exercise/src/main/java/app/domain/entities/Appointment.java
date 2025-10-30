package app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import app.domain.enums.AppointmentStatus;

/**
 * Entidad que representa una cita médica dentro del sistema.
 * Contiene información del paciente, médico, fecha, especialidad y estado.
 */
@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    // Identificador único de la cita
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del paciente asociado a la cita
    @Column(nullable = false, length = 60)
    @NotBlank(message = "El nombre del paciente es obligatorio.")
    private String patientName;

    // Documento de identificación del paciente
    @Column(nullable = false, length = 20)
    @NotBlank(message = "El documento del paciente es obligatorio.")
    private String patientDocument;

    // Nombre del médico asignado a la cita
    @Column(nullable = false, length = 60)
    @NotBlank(message = "El nombre del médico es obligatorio.")
    private String doctorName;

    // Especialidad médica de la cita
    @Column(nullable = false, length = 50)
    @NotBlank(message = "La especialidad es obligatoria.")
    private String specialty;

    // Fecha y hora programadas para la cita
    @Column(nullable = false)
    @Future(message = "La fecha de la cita debe ser futura.")
    private LocalDateTime appointmentDate;

    // Estado actual de la cita (ejemplo: SCHEDULED, COMPLETED, CANCELED)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
}
