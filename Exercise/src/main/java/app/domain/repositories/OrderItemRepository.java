package app.domain.repositories;

import app.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad OrderItem.
 * Permite realizar operaciones CRUD sobre los elementos o ítems asociados a una orden médica.
 * No se definen métodos personalizados en esta interfaz.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }
