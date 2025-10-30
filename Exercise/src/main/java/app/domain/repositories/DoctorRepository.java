package app.domain.repositories;

import app.domain.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Doctor.
 * Permite realizar operaciones CRUD y consultas personalizadas sobre los médicos registrados.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Busca un médico por su número de documento.
     * @param documentNumber número de documento del médico.
     * @return un Optional con el médico encontrado o vacío si no existe.
     */
    Optional<Doctor> findByDocumentNumber(String documentNumber);

    /**
     * Busca un médico por su número de licencia profesional.
     * @param licenseNumber número de licencia del médico.
     * @return un Optional con el médico encontrado o vacío si no existe.
     */
    Optional<Doctor> findByLicenseNumber(String licenseNumber);

    /**
     * Verifica si existe un médico con el número de documento indicado.
     * @param documentNumber número de documento del médico.
     * @return true si existe, false en caso contrario.
     */
    boolean existsByDocumentNumber(String documentNumber);

    /**
     * Verifica si existe un médico con el número de licencia indicado.
     * @param licenseNumber número de licencia del médico.
     * @return true si existe, false en caso contrario.
     */
    boolean existsByLicenseNumber(String licenseNumber);
}
