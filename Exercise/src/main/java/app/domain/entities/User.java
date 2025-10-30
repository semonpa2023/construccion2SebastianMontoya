package app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * Entidad que representa a un usuario del sistema.
 * Contiene información personal, credenciales y rol asignado.
 * Incluye validaciones automáticas mediante anotaciones y una validación personalizada de edad.
 */
@Entity
@Table(name = "users")
public class User {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del usuario (entre 3 y 50 caracteres)
    @NotBlank(message = "El nombre completo no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre completo debe tener entre 3 y 50 caracteres.")
    private String fullName;

    // Número de documento único
    @NotBlank(message = "El número de cédula es obligatorio.")
    @Column(unique = true)
    private String documentNumber;

    // Correo electrónico del usuario
    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "El formato de correo no es válido.")
    @Column(unique = true)
    private String email;

    // Número de teléfono de contacto
    @NotBlank(message = "El teléfono es obligatorio.")
    private String phone;

    // Fecha de nacimiento del usuario
    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    private LocalDate birthDate;

    // Dirección residencial
    @Size(max = 30, message = "La dirección no puede exceder los 30 caracteres.")
    private String address;

    // Rol asignado (ej: "ADMIN", "DOCTOR", "NURSE")
    @NotBlank(message = "El rol es obligatorio.")
    private String role;

    // Nombre de usuario único
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Column(unique = true)
    private String username;

    // Contraseña del usuario
    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    /**
     * Verifica que la edad del usuario sea válida (entre 1 y 150 años).
     * @return true si la edad está dentro del rango permitido, false en caso contrario.
     */
    public boolean isAgeValid() {
        if (birthDate == null) return false;
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return age > 0 && age <= 150;
    }
}
