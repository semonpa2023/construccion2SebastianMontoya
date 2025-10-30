package app.domain.repositories;

import app.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad User.
 * Permite realizar operaciones CRUD sobre los usuarios del sistema.
 * Incluye métodos personalizados para verificar la existencia de usuarios
 * por nombre de usuario o correo electrónico, así como para buscar usuarios
 * por nombre de usuario o número de documento.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByDocumentNumber(String documentNumber);
}
