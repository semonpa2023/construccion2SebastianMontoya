package app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa un contacto de emergencia asociado a un paciente.
 * Contiene información básica de identificación y relación.
 */
@Entity
@Getter
@Setter
public class EmergencyContact {

    // Identificador único autogenerado para cada contacto de emergencia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del contacto de emergencia
    @Column(nullable = false, length = 60)
    private String fullName;

    // Relación del contacto con el paciente (ejemplo: padre, madre, cónyuge)
    @Column(nullable = false, length = 40)
    private String relationship;

    // Número de teléfono del contacto (10 dígitos)
    @Column(nullable = false, length = 15)
    private String phone;

    // Asociación uno a uno con el paciente correspondiente
    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
