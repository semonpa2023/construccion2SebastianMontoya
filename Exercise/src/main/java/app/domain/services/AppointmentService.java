package app.domain.services;

import app.domain.entities.Appointment;
import app.domain.enums.AppointmentStatus;
import app.domain.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio para la gestión de citas médicas.
 * Encapsula la lógica de negocio relacionada con la creación, consulta, actualización de estado
 * y cancelación de citas médicas.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    /**
     * Registra una nueva cita médica.
     * Verifica que la fecha de la cita sea futura antes de guardarla.
     * @param appointment Objeto Appointment a registrar.
     * @return La cita registrada.
     * @throws IllegalArgumentException si la fecha de la cita es pasada.
     */
    public Appointment registerAppointment(Appointment appointment) {
        if (appointment.getAppointmentDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de la cita debe ser futura.");
        }
        return appointmentRepository.save(appointment);
    }

    /**
     * Obtiene todas las citas médicas registradas.
     * @return Lista de citas.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Obtiene todas las citas de un paciente específico mediante su número de documento.
     * @param document Número de documento del paciente.
     * @return Lista de citas del paciente.
     */
    public List<Appointment> getAppointmentsByPatient(String document) {
        return appointmentRepository.findByPatientDocument(document);
    }

    /**
     * Actualiza el estado de una cita existente.
     * @param id Identificador de la cita.
     * @param newStatus Nuevo estado de la cita.
     * @throws IllegalArgumentException si la cita no existe.
     */
    public void updateStatus(Long id, AppointmentStatus newStatus) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));
        appointment.setStatus(newStatus);
        appointmentRepository.save(appointment);
    }

    /**
     * Cancela una cita médica existente.
     * Cambia su estado a CANCELED.
     * @param id Identificador de la cita a cancelar.
     * @throws IllegalArgumentException si la cita no existe.
     */
    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentRepository.save(appointment);
    }
}
