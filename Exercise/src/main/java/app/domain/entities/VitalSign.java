package app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Entidad que representa los signos vitales de un paciente durante una atención médica.
 * Cada registro almacena los valores clínicos medidos y la fecha del registro.
 */
@Entity
@Table(name = "vital_signs")
@Getter
@Setter
public class VitalSign {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el paciente al que pertenecen los signos vitales
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Presión arterial (en mmHg)
    @Column(nullable = false)
    private double bloodPressure;

    // Temperatura corporal (en grados Celsius)
    @Column(nullable = false)
    private double temperature;

    // Pulso cardiaco (latidos por minuto)
    @Column(nullable = false)
    private double pulse;

    // Nivel de oxígeno en sangre (porcentaje)
    @Column(nullable = false)
    private double oxygenLevel;

    // Fecha y hora en que se registraron los signos vitales
    @Column(nullable = false)
    private LocalDateTime recordedAt;

    /**
     * Devuelve una representación legible del registro de signos vitales.
     * @return cadena con los valores clínicos formateados.
     */
    @Override
    public String toString() {
        return String.format(
            "Presión: %.1f mmHg | Temp: %.1f°C | Pulso: %.0f lpm | O₂: %.0f%% | Fecha: %s",
            bloodPressure, temperature, pulse, oxygenLevel, recordedAt
        );
    }
}
