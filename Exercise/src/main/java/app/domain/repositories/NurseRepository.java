package app.domain.repositories;

import app.domain.entities.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Nurse.
 * Permite realizar operaciones CRUD sobre los registros de enfermeras.
 * Incluye métodos personalizados para buscar una enfermera por su número de documento
 * y verificar la existencia de una enfermera mediante su número de licencia profesional.
 */
@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findByDocumentNumber(String documentNumber);

    boolean existsByLicenseNumber(String licenseNumber);
}
