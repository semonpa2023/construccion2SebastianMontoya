package app.domain.repositories;

import app.domain.entities.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio JPA para la entidad Procedure.
 * Permite realizar operaciones CRUD sobre los procedimientos médicos registrados.
 * Incluye un método personalizado para buscar procedimientos cuyo nombre contenga
 * una cadena específica, sin distinguir entre mayúsculas y minúsculas.
 */
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

    List<Procedure> findByNameContainingIgnoreCase(String name);
}
