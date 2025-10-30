package app.domain.services;

import app.domain.entities.DiagnosticAid;
import app.domain.repositories.DiagnosticAidRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para la gestión de ayudas diagnósticas.
 * Proporciona métodos para crear, listar y eliminar registros de ayudas diagnósticas.
 */
@Service
public class DiagnosticAidService {

    private final DiagnosticAidRepository repository;

    public DiagnosticAidService(DiagnosticAidRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un registro de ayuda diagnóstica en la base de datos.
     * @param aid Objeto DiagnosticAid a guardar.
     * @return La ayuda diagnóstica guardada.
     */
    public DiagnosticAid save(DiagnosticAid aid) {
        return repository.save(aid);
    }

    /**
     * Obtiene todos los registros de ayudas diagnósticas existentes.
     * @return Lista de ayudas diagnósticas.
     */
    public List<DiagnosticAid> findAll() {
        return repository.findAll();
    }

    /**
     * Elimina un registro de ayuda diagnóstica por su ID.
     * @param id Identificador de la ayuda diagnóstica a eliminar.
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
