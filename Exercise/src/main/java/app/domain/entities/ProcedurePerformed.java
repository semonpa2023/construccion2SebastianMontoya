package app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa un procedimiento realizado por una enfermera
 * durante la atención de un paciente. Está asociado a un registro de enfermería.
 */
@Entity
@Table(name = "procedures_performed")
@Getter
@Setter
public class ProcedurePerformed {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el registro de enfermería
    @ManyToOne
    @JoinColumn(name = "nurse_record_id")
    private NurseRecord nurseRecord;

    // Relación con el procedimiento ejecutado
    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;

    // Observaciones adicionales registradas durante el procedimiento
    private String observations;

    // Representación legible del procedimiento en texto
    @Override
    public String toString() {
        return "Procedimiento: " + (procedure != null ? procedure.getName() : "N/A") +
               " - Observación: " + (observations != null ? observations : "");
    }
}
