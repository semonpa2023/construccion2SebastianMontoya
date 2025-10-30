package app.domain.services;

import app.domain.entities.Procedure;
import app.domain.repositories.ProcedureRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para la gestión de procedimientos médicos.
 * Proporciona métodos para crear, listar y eliminar registros de procedimientos.
 */
@Service
public class ProcedureService {

    private final ProcedureRepository repository;

    public ProcedureService(ProcedureRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un procedimiento médico en la base de datos.
     * @param procedure Objeto Procedure a guardar.
     * @return El procedimiento guardado.
     */
    public Procedure save(Procedure procedure) {
        return repository.save(procedure);
    }

    /**
     * Obtiene todos los procedimientos médicos registrados.
     * @return Lista de procedimientos.
     */
    public List<Procedure> findAll() {
        return repository.findAll();
    }

    /**
     * Elimina un procedimiento médico por su ID.
     * @param id Identificador del procedimiento a eliminar.
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
