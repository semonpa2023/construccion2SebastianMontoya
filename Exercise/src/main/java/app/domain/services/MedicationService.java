package app.domain.services;

import app.domain.entities.Medication;
import app.domain.repositories.MedicationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para la gestión de medicamentos.
 * Proporciona métodos para crear, listar y eliminar registros de medicamentos.
 */
@Service
public class MedicationService {

    private final MedicationRepository repository;

    public MedicationService(MedicationRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un medicamento en la base de datos.
     * @param medication Objeto Medication a guardar.
     * @return El medicamento guardado.
     */
    public Medication save(Medication medication) {
        return repository.save(medication);
    }

    /**
     * Obtiene todos los medicamentos registrados.
     * @return Lista de medicamentos.
     */
    public List<Medication> findAll() {
        return repository.findAll();
    }

    /**
     * Elimina un medicamento por su ID.
     * @param id Identificador del medicamento a eliminar.
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
