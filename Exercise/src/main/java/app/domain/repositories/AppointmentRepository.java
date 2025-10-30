package app.domain.repositories;

import app.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio JPA para la entidad Appointment.
 * Permite realizar operaciones CRUD y consultas personalizadas sobre las citas médicas.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * Obtiene todas las citas asociadas a un paciente por su número de documento.
     * @param document número de documento del paciente.
     * @return lista de citas del paciente.
     */
    List<Appointment> findByPatientDocument(String document);

    /**
     * Busca las citas asociadas a un médico por su nombre.
     * @param doctorName nombre completo del médico.
     * @return lista de citas del médico.
     */
    List<Appointment> findByDoctorName(String doctorName);

    /**
     * Retorna las citas programadas dentro de un rango de fechas.
     * @param start fecha de inicio del rango.
     * @param end fecha final del rango.
     * @return lista de citas dentro del rango de tiempo.
     */
    List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
}
