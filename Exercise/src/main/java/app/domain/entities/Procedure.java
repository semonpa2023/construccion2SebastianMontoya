package app.domain.entities;

import jakarta.persistence.*;

/**
 * Entidad que representa un procedimiento médico dentro del sistema.
 * Cada procedimiento tiene un nombre, descripción y costo asociado.
 */
@Entity
@Table(name = "procedures")
public class Procedure {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del procedimiento
    private String name;

    // Descripción breve del procedimiento
    private String description;

    // Costo del procedimiento
    private double cost;

    // Constructor vacío requerido por JPA
    public Procedure() {}

    // Getters y Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    // Representación en texto utilizada en vistas o mensajes
    @Override
    public String toString() {
        return "Nombre: " + name +
               " | Descripción: " + description +
               " | Costo: $" + cost;
    }
}
