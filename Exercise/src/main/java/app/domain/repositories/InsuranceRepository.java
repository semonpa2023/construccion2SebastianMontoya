package app.domain.repositories;

import app.domain.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Insurance.
 * Permite realizar operaciones CRUD sobre las pólizas de seguro médico.
 */
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    // No se requieren métodos personalizados por ahora.
}
