package app.domain.services;

import app.domain.entities.NurseRecord;
import app.domain.entities.Patient;
import app.domain.repositories.NurseRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que gestiona las visitas de enfermería (NurseRecord).
 */
@Service
public class NurseRecordService {

    private final NurseRecordRepository nurseRecordRepository;

    public NurseRecordService(NurseRecordRepository nurseRecordRepository) {
        this.nurseRecordRepository = nurseRecordRepository;
    }

    // Guardar una visita de enfermería
    public NurseRecord save(NurseRecord record) {
        return nurseRecordRepository.save(record);
    }

    // Obtener todas las visitas registradas
    public List<NurseRecord> findAll() {
        return nurseRecordRepository.findAll();
    }

    // Buscar las visitas de un paciente específico
    public List<NurseRecord> findByPatient(Patient patient) {
        return nurseRecordRepository.findByPatient(patient);
    }

    // Eliminar una visita por su ID
    public void delete(Long id) {
        nurseRecordRepository.deleteById(id);
    }
}
