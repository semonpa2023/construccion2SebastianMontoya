package app.domain.repositories;

import app.domain.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio JPA para la entidad Medication.
 * Permite realizar operaciones CRUD sobre los medicamentos registrados.
 * Incluye un método personalizado para buscar medicamentos cuyo nombre contenga
 * una cadena específica, sin distinguir entre mayúsculas y minúsculas.
 */
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByNameContainingIgnoreCase(String name);

}
