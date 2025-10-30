package app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa a una enfermera dentro del sistema clínico.
 * Contiene información personal, profesional y de contacto.
 */
@Entity
@Table(name = "nurses")
@Getter
@Setter
public class Nurse {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo de la enfermera
    @Column(nullable = false, length = 60)
    @NotBlank(message = "El nombre completo es obligatorio.")
    private String fullName;

    // Número de documento único de la enfermera
    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "El número de documento es obligatorio.")
    private String documentNumber;

    // Correo electrónico institucional
    @Column(length = 60)
    @Email(message = "El correo electrónico no tiene un formato válido.")
    private String email;

    // Área de trabajo asignada (ejemplo: urgencias, pediatría, cirugía)
    @Column(nullable = false, length = 40)
    @NotBlank(message = "El área de trabajo es obligatoria.")
    private String workArea;

    // Número de licencia profesional único
    @Column(nullable = false, unique = true, length = 30)
    @NotBlank(message = "El número de licencia es obligatorio.")
    private String licenseNumber;

    // Estado de la enfermera (true = activa, false = inactiva)
    @Column(nullable = false)
    private boolean active = true;
}
