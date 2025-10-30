package app.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.entities.Admin;

/**
 * Repositorio JPA para la entidad Admin.
 * Permite realizar operaciones CRUD sobre los administradores del sistema.
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * Busca un administrador por su nombre de usuario.
     * @param username nombre de usuario del administrador.
     * @return el administrador correspondiente o null si no existe.
     */
    Admin findByUsername(String username);
}
