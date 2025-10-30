package app.domain.services;

import app.domain.entities.Patient;
import app.domain.entities.Insurance;
import org.springframework.stereotype.Service;

/**
 * Servicio para el cálculo de facturación médica.
 * Determina el valor a pagar por un paciente considerando su seguro médico,
 * el costo total de los servicios y el copago acumulado.
 */
@Service
public class BillingService {

    /**
     * Calcula el monto a pagar por un paciente.
     * Si el paciente no tiene seguro activo, se retorna el costo total.
     * Si el seguro está activo, se aplica un copago fijo, el cual puede ser cero
     * si el copago acumulado supera un umbral determinado.
     *
     * @param patient Paciente al que se le calcula la factura.
     * @param insurance Seguro médico del paciente.
     * @param totalCost Costo total de los servicios.
     * @param accumulatedCopay Copago acumulado del paciente.
     * @return Monto a pagar por el paciente.
     */
    public double calculateBill(Patient patient, Insurance insurance, double totalCost, double accumulatedCopay) {
        if (insurance == null || !insurance.isActive()) {
            return totalCost; // Sin seguro activo
        }

        double copay = 50000;
        if (accumulatedCopay >= 1000000) copay = 0;

        return copay;
    }
}
