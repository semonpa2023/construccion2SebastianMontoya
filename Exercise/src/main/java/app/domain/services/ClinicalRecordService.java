package app.domain.services;

import app.domain.entities.ClinicalRecord;
import app.domain.entities.Patient;
import app.domain.repositories.ClinicalRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para la gestión de registros clínicos de pacientes.
 * Proporciona métodos para crear, consultar por paciente y listar todos los registros clínicos.
 */
@Service
public class ClinicalRecordService {

    private final ClinicalRecordRepository repository;

    public ClinicalRecordService(ClinicalRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un registro clínico en la base de datos.
     * @param record Registro clínico a guardar.
     * @return Registro clínico guardado.
     */
    public ClinicalRecord save(ClinicalRecord record) {
        return repository.save(record);
    }

    /**
     * Obtiene todos los registros clínicos asociados a un paciente específico.
     * @param patient Paciente cuyos registros se desean consultar.
     * @return Lista de registros clínicos del paciente.
     */
    public List<ClinicalRecord> findByPatient(Patient patient) {
        return repository.findByPatient(patient);
    }

    /**
     * Obtiene todos los registros clínicos existentes.
     * @return Lista completa de registros clínicos.
     */
    public List<ClinicalRecord> findAll() {
        return repository.findAll();
    }
}
