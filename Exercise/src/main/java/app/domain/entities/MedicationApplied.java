package app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa un medicamento administrado por una enfermera durante una atención al paciente.
 * Cada registro está asociado a una visita específica y a un medicamento aplicado.
 */
@Entity
@Table(name = "medications_applied")
@Getter
@Setter
public class MedicationApplied {

    // Identificador único del registro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el registro de enfermería correspondiente
    @ManyToOne
    @JoinColumn(name = "nurse_record_id")
    private NurseRecord nurseRecord;

    // Medicamento aplicado durante la atención
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    // Dosis administrada del medicamento
    private String dose;

    // Observaciones adicionales registradas por la enfermera
    private String observations;

    // Representación en texto del medicamento aplicado
    @Override
    public String toString() {
        return "Medicamento: " + (medication != null ? medication.getName() : "N/A") +
               " - Dosis: " + dose +
               " - Observación: " + (observations != null ? observations : "");
    }
}
