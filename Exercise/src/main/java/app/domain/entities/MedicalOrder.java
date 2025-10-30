package app.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una orden médica.
 * Cada orden puede contener varios ítems (medicamentos, procedimientos o ayudas diagnósticas).
 */
@Entity
@Table(name = "medical_orders")
public class MedicalOrder {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Número único de orden de seis dígitos
    @Column(name = "order_number", length = 6, unique = true, nullable = false)
    private String orderNumber;

    // Documento del paciente al que pertenece la orden
    @Column(name = "patient_document", length = 20, nullable = false)
    private String patientDocument;

    // Documento del médico que genera la orden
    @Column(name = "doctor_document", length = 20, nullable = false)
    private String doctorDocument;

    // Fecha de creación de la orden médica
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relación uno a muchos: una orden puede tener varios ítems
    @OneToMany(mappedBy = "medicalOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // Métodos de acceso (getters y setters)
    public Long getId() { return id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getPatientDocument() { return patientDocument; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }

    public String getDoctorDocument() { return doctorDocument; }
    public void setDoctorDocument(String doctorDocument) { this.doctorDocument = doctorDocument; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public List<OrderItem> getItems() { return items; }

    // Agrega un ítem a la orden
    public void addItem(OrderItem item) {
        items.add(item);
        item.setMedicalOrder(this);
    }

    // Elimina un ítem de la orden
    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setMedicalOrder(null);
    }
}
