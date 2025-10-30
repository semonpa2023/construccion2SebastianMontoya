package app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Entidad que representa un registro clínico general.
 * Puede ser un registro de medicación, procedimiento o evento clínico asociado a un paciente.
 */
@Entity
@Table(name = "clinical_records")
@Getter
@Setter
public class ClinicalRecord {

    // Identificador único del registro clínico
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tipo de registro (por ejemplo: MEDICAMENTO o PROCEDIMIENTO)
    @Column(nullable = false)
    private String recordType;

    // Observaciones o comentarios sobre el evento clínico
    @Column(length = 300)
    private String observation;

    // Relación con el paciente al que pertenece este registro
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Medicamento asociado (si aplica)
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    // Procedimiento asociado (si aplica)
    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;

    // Dosis aplicada (solo si el registro corresponde a un medicamento)
    private String dose;

    // Fecha y hora de creación del registro
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Método para mostrar el registro clínico en formato legible
    @Override
    public String toString() {
        return "Tipo: " + recordType +
                " | Fecha: " + createdAt +
                (medication != null ? " | Medicamento: " + medication.getName() : "") +
                (procedure != null ? " | Procedimiento: " + procedure.getName() : "") +
                (dose != null ? " | Dosis: " + dose : "") +
                (observation != null ? " | Obs: " + observation : "");
    }
}
