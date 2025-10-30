package app.domain.repositories;

import app.domain.entities.NurseRecord;
import app.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para la entidad NurseRecord.
 * Permite realizar operaciones CRUD sobre los registros de visitas de enfermería.
 * Incluye un método personalizado para obtener todos los registros asociados a un paciente específico.
 */
@Repository
public interface NurseRecordRepository extends JpaRepository<NurseRecord, Long> {
    List<NurseRecord> findByPatient(Patient patient);
}
