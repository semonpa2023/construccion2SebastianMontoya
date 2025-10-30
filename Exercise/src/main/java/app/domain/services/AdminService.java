package app.domain.services;

import java.util.List;
import org.springframework.stereotype.Service;
import app.domain.entities.Admin;
import app.domain.repositories.AdminRepository;

/**
 * Servicio para la entidad Admin.
 * Gestiona la lógica de negocio relacionada con los administradores del sistema,
 * incluyendo creación, listado, búsqueda por nombre de usuario y eliminación de administradores.
 */
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Registra un nuevo administrador en el sistema.
     * @param admin Objeto Admin a registrar.
     * @return El administrador guardado.
     */
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    /**
     * Obtiene la lista de todos los administradores registrados.
     * @return Lista de administradores.
     */
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Busca un administrador por su nombre de usuario.
     * @param username Nombre de usuario del administrador.
     * @return Administrador encontrado.
     */
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    /**
     * Elimina un administrador según su ID.
     * @param id Identificador del administrador a eliminar.
     */
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
