package app.domain.repositories;

import app.domain.entities.MedicalOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad MedicalOrder.
 * Proporciona métodos para realizar operaciones CRUD sobre las órdenes médicas.
 * Incluye consultas personalizadas para buscar y verificar la existencia de una orden médica
 * mediante su número de orden.
 */
public interface MedicalOrderRepository extends JpaRepository<MedicalOrder, Long> {
    Optional<MedicalOrder> findByOrderNumber(String orderNumber);
    boolean existsByOrderNumber(String orderNumber);
}
