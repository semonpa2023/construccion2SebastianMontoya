package app.domain.repositories;

import app.domain.entities.ProcedurePerformed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad ProcedurePerformed.
 * Permite realizar operaciones CRUD sobre los registros de procedimientos médicos realizados.
 * No se definen métodos personalizados en esta interfaz.
 */
public interface ProcedurePerformedRepository extends JpaRepository<ProcedurePerformed, Long> {}
