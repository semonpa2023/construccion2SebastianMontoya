package app.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que representa la póliza de seguro médico asociada a un paciente.
 * Contiene información sobre la compañía, número de póliza y vigencia.
 */
@Entity
@Table(name = "insurances")
public class Insurance {

    // Identificador único autogenerado para cada póliza
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la compañía aseguradora
    @Column(nullable = false, length = 60)
    private String companyName;

    // Número de póliza asignado al paciente
    @Column(nullable = false, unique = true, length = 30)
    private String policyNumber;

    // Indica si la póliza se encuentra activa
    @Column(nullable = false)
    private boolean active;

    // Fecha de expiración o fin de vigencia del seguro
    @Column(nullable = false)
    private LocalDate validUntil;

    // Relación uno a uno con el paciente asociado
    @OneToOne(mappedBy = "insurance")
    private Patient patient;

    // Getters y Setters
    public Long getId() { return id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDate getValidUntil() { return validUntil; }
    public void setValidUntil(LocalDate validUntil) { this.validUntil = validUntil; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
