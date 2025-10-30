package app.domain.services;

import app.domain.entities.VitalSign;
import app.domain.entities.Patient;
import app.domain.repositories.VitalSignRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para la gestión de signos vitales de pacientes.
 * Proporciona métodos para registrar signos vitales, consultar por paciente y listar todos los registros.
 */
@Service
public class VitalSignService {

    private final VitalSignRepository repository;

    public VitalSignService(VitalSignRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un registro de signos vitales en la base de datos.
     * @param vitalSign Objeto VitalSign a guardar.
     * @return El registro de signos vitales guardado.
     */
    public VitalSign save(VitalSign vitalSign) {
        return repository.save(vitalSign);
    }

    /**
     * Obtiene todos los registros de signos vitales asociados a un paciente específico.
     * @param patient Paciente cuyos signos vitales se desean consultar.
     * @return Lista de signos vitales del paciente.
     */
    public List<VitalSign> findByPatient(Patient patient) {
        return repository.findByPatient(patient);
    }

    /**
     * Obtiene todos los registros de signos vitales existentes.
     * @return Lista de signos vitales.
     */
    public List<VitalSign> findAll() {
        return repository.findAll();
    }
}
