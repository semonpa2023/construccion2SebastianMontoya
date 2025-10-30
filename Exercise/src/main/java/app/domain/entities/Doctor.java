package app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa a un médico dentro del sistema.
 * Incluye información personal, profesional y de contacto.
 */
@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {

    // Identificador único autogenerado para cada médico
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del médico
    @Column(nullable = false, length = 60)
    @NotBlank(message = "El nombre completo es obligatorio.")
    private String fullName;

    // Documento de identificación del médico (único)
    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "El número de documento es obligatorio.")
    private String documentNumber;

    // Correo electrónico institucional del médico
    @Column(length = 60)
    @Email(message = "El correo electrónico no tiene un formato válido.")
    private String email;

    // Especialidad médica del profesional (ejemplo: pediatría, cardiología)
    @Column(nullable = false, length = 40)
    @NotBlank(message = "La especialidad es obligatoria.")
    private String specialty;

    // Número de licencia profesional médica (único)
    @Column(nullable = false, unique = true, length = 30)
    @NotBlank(message = "El número de licencia es obligatorio.")
    private String licenseNumber;

    // Estado actual del médico dentro del sistema (activo o inactivo)
    @Column(nullable = false)
    private boolean active = true;
}
