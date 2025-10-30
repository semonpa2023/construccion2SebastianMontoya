package app.domain.repositories;

import app.domain.entities.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad EmergencyContact.
 * Permite realizar operaciones CRUD sobre los contactos de emergencia de los pacientes.
 */
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {}
