package app.domain.repositories;

import app.domain.entities.ClinicalRecord;
import app.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio JPA para la entidad ClinicalRecord.
 * Permite gestionar la persistencia y consultas relacionadas con los registros clínicos de los pacientes.
 */
public interface ClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {

    /**
     * Obtiene todos los registros clínicos asociados a un paciente específico.
     * @param patient instancia del paciente.
     * @return lista de registros clínicos pertenecientes al paciente.
     */
    List<ClinicalRecord> findByPatient(Patient patient);
}
