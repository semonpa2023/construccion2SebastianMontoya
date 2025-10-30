package app.domain.services;

import app.domain.entities.MedicalOrder;
import app.domain.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Lógica de alto nivel para acciones del médico:
 * - crear consulta en historia NoSQL
 * - crear orden médica
 * - procesar resultado de ayuda diagnóstica y generar diagnóstico final
 */
@Service
public class DoctorService {

    private final ClinicalHistoryService historyService;
    private final OrderService orderService;

    public DoctorService(ClinicalHistoryService historyService, OrderService orderService) {
        this.historyService = historyService;
        this.orderService = orderService;
    }

    /**
     * Crea una entrada (consulta) en la historia clínica NoSQL (simulada).
     */
    public void createConsultation(String patientDocument, String doctorDocument, LocalDateTime at,
                                   String reason, String symptoms, String initialDiagnosis) {
        Map<String, Object> data = new HashMap<>();
        data.put("date", at.toString());
        data.put("doctorDocument", doctorDocument);
        data.put("reason", reason);
        data.put("symptoms", symptoms);
        data.put("initialDiagnosis", initialDiagnosis);
        // finalDiagnosis se agrega cuando llega resultado
        historyService.addConsultation(patientDocument, at, data);
    }

    /**
     * Crear orden y devolverla.
     */
    public MedicalOrder createOrder(String patientDocument, String doctorDocument) {
        return orderService.createOrder(patientDocument, doctorDocument);
    }

    public OrderItem addItemToOrder(MedicalOrder order, OrderItem item) {
        return orderService.addItem(order, item);
    }

    /**
     * Cuando llega el resultado de una ayuda diagnóstica, generar diagnóstico final
     * y actualizar la historia clínica (se asume que la orden y el ítem identifican la consulta).
     */
    public boolean processDiagnosticResult(String patientDocument, LocalDateTime consultationAt, String diagnosticResult, String finalDiagnosis) {
        // guardar resultado (como ejemplo lo añadimos en la subclave)
        Map<String, Object> result = new HashMap<>();
        result.put("diagnosticResult", diagnosticResult);
        result.put("finalDiagnosis", finalDiagnosis);
        result.put("finalizedAt", LocalDateTime.now().toString());

        // intentar usar el servicio NoSQL para insertar el diagnóstico final
        return historyService.addFinalDiagnosis(patientDocument, consultationAt, finalDiagnosis);
    }
}
