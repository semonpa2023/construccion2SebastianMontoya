package app.domain.entities;

import jakarta.persistence.*;

/**
 * Entidad que representa al administrador general del sistema.
 * Mapeada a la tabla 'admins' en la base de datos.
 */
@Entity
@Table(name = "admins")
public class Admin {

    // Identificador único del administrador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del administrador
    @Column(nullable = false, length = 60)
    private String fullName;

    // Nombre de usuario, único en el sistema
    @Column(nullable = false, unique = true, length = 15)
    private String username;

    // Contraseña del administrador
    @Column(nullable = false)
    private String password;

    // Constructor vacío requerido por JPA
    public Admin() {}

    // Constructor con parámetros principales
    public Admin(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    // Métodos de acceso (getters y setters)
    public Long getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
