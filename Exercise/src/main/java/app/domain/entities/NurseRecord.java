package app.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una visita o atención realizada por una enfermera a un paciente.
 * Registra el motivo, observaciones y fecha de la visita.
 */
@Entity
@Table(name = "nurse_records")
public class NurseRecord {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la enfermera que realizó la visita
    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = false)
    private Nurse nurse;

    // Relación con el paciente atendido
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Motivo principal de la visita
    @Column(name = "visit_reason", nullable = false)
    private String visitReason;

    // Observaciones registradas durante la atención
    @Column(name = "observations")
    private String observations;

    // Fecha y hora en que se realizó la visita
    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }
}
