package app.domain.entities;

import app.domain.enums.ItemType;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa un ítem dentro de una orden médica.
 * Cada ítem puede ser un medicamento, procedimiento o ayuda diagnóstica.
 * Contiene campos flexibles para soportar diferentes tipos de órdenes.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tipo de ítem (MEDICAMENTO, PROCEDIMIENTO o AYUDA_DIAGNOSTICA)
    @Enumerated(EnumType.STRING)
    private ItemType type;

    // Código o número del ítem (puede representar un código o identificador interno)
    private String itemCode;

    // Nombre descriptivo del ítem (nombre del medicamento, procedimiento, examen, etc.)
    private String itemName;

    // Dosis aplicada (solo para medicamentos)
    private String dose;

    // Duración del tratamiento (solo para medicamentos)
    private String duration;

    // Cantidad total (usado en medicamentos o ayudas diagnósticas)
    private Integer quantity;

    // Repeticiones del procedimiento (usado en procedimientos)
    private Integer repetitions;

    // Frecuencia de aplicación o realización (ejemplo: "2 veces al día")
    private String frequency;

    // Costo del ítem
    @Column(precision = 12, scale = 2)
    private BigDecimal cost;

    // Indica si requiere la intervención de un especialista
    private Boolean requiresSpecialist = false;

    // Identificador de la especialidad si aplica
    private Long specialtyId;

    // Relación con la orden médica principal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private MedicalOrder medicalOrder;

    // Getters y Setters
    public Long getId() { return id; }

    public ItemType getType() { return type; }
    public void setType(ItemType type) { this.type = type; }

    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getRepetitions() { return repetitions; }
    public void setRepetitions(Integer repetitions) { this.repetitions = repetitions; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public Boolean getRequiresSpecialist() { return requiresSpecialist; }
    public void setRequiresSpecialist(Boolean requiresSpecialist) { this.requiresSpecialist = requiresSpecialist; }

    public Long getSpecialtyId() { return specialtyId; }
    public void setSpecialtyId(Long specialtyId) { this.specialtyId = specialtyId; }

    public MedicalOrder getMedicalOrder() { return medicalOrder; }
    public void setMedicalOrder(MedicalOrder medicalOrder) { this.medicalOrder = medicalOrder; }
}
