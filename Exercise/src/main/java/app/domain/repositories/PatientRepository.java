package app.domain.repositories;

import app.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Patient.
 * Permite realizar operaciones CRUD sobre los registros de pacientes.
 * Incluye métodos personalizados para buscar un paciente por su número de documento
 * y verificar si existe un registro con un número de documento específico.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumber(String documentNumber);
}
