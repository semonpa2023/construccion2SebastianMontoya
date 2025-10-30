package app.domain.repositories;

import app.domain.entities.MedicationApplied;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad MedicationApplied.
 * Permite realizar operaciones CRUD sobre los registros de medicamentos aplicados a los pacientes.
 * No se definen métodos personalizados en esta interfaz.
 */
public interface MedicationAppliedRepository extends JpaRepository<MedicationApplied, Long> {}
