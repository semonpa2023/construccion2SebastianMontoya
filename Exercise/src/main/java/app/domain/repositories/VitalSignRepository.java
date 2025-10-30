package app.domain.repositories;

import app.domain.entities.VitalSign;
import app.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio JPA para la entidad VitalSign.
 * Permite realizar operaciones CRUD sobre los registros de signos vitales de los pacientes.
 * Incluye un método personalizado para obtener todos los signos vitales asociados a un paciente específico.
 */
public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    List<VitalSign> findByPatient(Patient patient);
}
