package app.domain.entities;

import jakarta.persistence.*;

/**
 * Entidad que representa una ayuda diagnóstica dentro del sistema.
 * Las ayudas diagnósticas son exámenes o pruebas médicas con su respectivo costo.
 */
@Entity
public class DiagnosticAid {

    // Identificador único de la ayuda diagnóstica
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la ayuda diagnóstica
    private String name;

    // Descripción o detalle del examen o prueba
    private String description;

    // Costo asociado a la ayuda diagnóstica
    private double cost;

    // Métodos getter y setter
    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getCost() { return cost; }

    public void setCost(double cost) { this.cost = cost; }

    // Representación en texto de la ayuda diagnóstica
    @Override
    public String toString() {
        return name + " - $" + cost;
    }
}
