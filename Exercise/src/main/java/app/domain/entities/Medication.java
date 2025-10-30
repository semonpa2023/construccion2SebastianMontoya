package app.domain.entities;

import jakarta.persistence.*;

/**
 * Entidad que representa un medicamento dentro del sistema.
 * Contiene información básica como nombre, descripción, cantidad disponible y precio.
 */
@Entity
public class Medication {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del medicamento
    private String name;

    // Descripción o detalles del medicamento
    private String description;

    // Cantidad disponible en inventario
    private int quantity;

    // Precio unitario del medicamento
    private double price;

    // Métodos de acceso (getters y setters)
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Representación en texto del medicamento
    @Override
    public String toString() {
        return name + " - $" + price + " (" + quantity + " unidades)";
    }
}
