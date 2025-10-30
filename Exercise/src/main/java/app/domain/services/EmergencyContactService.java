package app.domain.services;

import app.domain.entities.EmergencyContact;
import app.domain.repositories.EmergencyContactRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para la gestión de contactos de emergencia de los pacientes.
 * Proporciona métodos para crear, listar y eliminar contactos de emergencia.
 */
@Service
public class EmergencyContactService {

    private final EmergencyContactRepository repository;

    public EmergencyContactService(EmergencyContactRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un contacto de emergencia en la base de datos.
     * @param contact Objeto EmergencyContact a guardar.
     * @return El contacto de emergencia guardado.
     */
    public EmergencyContact save(EmergencyContact contact) {
        return repository.save(contact);
    }

    /**
     * Obtiene todos los contactos de emergencia registrados.
     * @return Lista de contactos de emergencia.
     */
    public List<EmergencyContact> findAll() {
        return repository.findAll();
    }

    /**
     * Elimina un contacto de emergencia por su ID.
     * @param id Identificador del contacto de emergencia a eliminar.
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
