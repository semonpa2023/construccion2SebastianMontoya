package app.domain.repositories;

import app.domain.entities.DiagnosticAid;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio JPA para la entidad DiagnosticAid.
 * Permite realizar operaciones CRUD y consultas personalizadas sobre ayudas diagnósticas.
 */
public interface DiagnosticAidRepository extends JpaRepository<DiagnosticAid, Long> {

    /**
     * Busca ayudas diagnósticas cuyo nombre contenga una cadena específica,
     * sin distinguir entre mayúsculas y minúsculas.
     * @param name parte del nombre a buscar.
     * @return lista de ayudas diagnósticas coincidentes.
     */
    List<DiagnosticAid> findByNameContainingIgnoreCase(String name);
}
