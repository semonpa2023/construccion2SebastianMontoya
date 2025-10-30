package app.infrastructure.gui;

import app.domain.entities.MedicalOrder;
import app.domain.services.ClinicalHistoryService;
import app.domain.services.DoctorService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;

/**
 * GUI (JOptionPane) para médicos: crear consulta, crear orden, añadir ítems y procesar resultado de ayudas diagnósticas.
 */
@Component
public class DoctorGUI {

    private final DoctorService doctorService;
    private final ClinicalHistoryService historyService;

    public DoctorGUI(DoctorService doctorService, ClinicalHistoryService historyService) {
        this.doctorService = doctorService;
        this.historyService = historyService;
    }

    /**
     * Muestra el menú principal para el médico.
     */
    public void showMenu() {
        String[] options = {
                "Crear consulta (historia NoSQL)",
                "Crear orden médica",
                "Agregar ítem a orden",
                "Procesar resultado ayuda diagnóstica",
                "Ver historia (simulada)",
                "Volver"
        };

        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Menú Médico:",
                    "Médico",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice < 0 || choice == 5) return;

            try {
                switch (choice) {
                    case 0 -> createConsultation();
                    case 1 -> createOrder();
                    case 2 -> addItemToOrder();
                    case 3 -> processDiagnosticResult();
                    case 4 -> viewHistory();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    private void createConsultation() {
        String patient = JOptionPane.showInputDialog("Cédula del paciente:");
        if (patient == null || patient.isBlank()) return;

        String doctor = JOptionPane.showInputDialog("Cédula del médico:");
        if (doctor == null || doctor.isBlank()) return;

        String reason = JOptionPane.showInputDialog("Motivo de consulta:");
        String symptoms = JOptionPane.showInputDialog("Sintomatología:");
        String initDiag = JOptionPane.showInputDialog("Diagnóstico inicial:");

        LocalDateTime now = LocalDateTime.now();
        doctorService.createConsultation(patient, doctor, now, reason, symptoms, initDiag);

        JOptionPane.showMessageDialog(null, "Consulta agregada a historia clínica (fecha: " + now + ").");
    }

    private void createOrder() {
        String patient = JOptionPane.showInputDialog("Cédula del paciente:");
        if (patient == null || patient.isBlank()) return;

        String doctor = JOptionPane.showInputDialog("Cédula del médico:");
        if (doctor == null || doctor.isBlank()) return;

        MedicalOrder order = doctorService.createOrder(patient, doctor);
        JOptionPane.showMessageDialog(null, "Orden creada: " + order.getOrderNumber());
    }

    private void addItemToOrder() {
        String orderNumber = JOptionPane.showInputDialog("Número de orden (6 dígitos):");
        if (orderNumber == null || orderNumber.isBlank()) return;

        // ⚠️ Nota: Aquí deberías reemplazar con la búsqueda real en tu servicio/repo:
        // MedicalOrder order = doctorService.getOrderByNumber(orderNumber);
        JOptionPane.showMessageDialog(null,
                "Nota: en esta versión de GUI debes usar la búsqueda real por número de orden.\n" +
                "Ejemplo: order = doctorService.getOrderByNumber(orderNumber);");
    }

    private void processDiagnosticResult() {
        String patient = JOptionPane.showInputDialog("Cédula del paciente:");
        if (patient == null || patient.isBlank()) return;

        String dateStr = JOptionPane.showInputDialog(
                "Fecha/hora de la consulta (ej: 2025-10-27T14:30) — debe coincidir con la subclave en la historia:");
        if (dateStr == null || dateStr.isBlank()) return;

        LocalDateTime at = LocalDateTime.parse(dateStr);

        String diagnosticResult = JOptionPane.showInputDialog("Resultado de la ayuda diagnóstica (texto):");
        String finalDiagnosis = JOptionPane.showInputDialog("Diagnóstico final:");

        boolean ok = doctorService.processDiagnosticResult(patient, at, diagnosticResult, finalDiagnosis);
        JOptionPane.showMessageDialog(null,
                ok ? "Diagnóstico final agregado a la historia." : "No se encontró la consulta para actualizar.");
    }

    private void viewHistory() {
        String patient = JOptionPane.showInputDialog("Cédula del paciente:");
        if (patient == null || patient.isBlank()) return;

        var hist = historyService.getHistory(patient);
        if (hist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay historia clínica para el paciente.");
            return;
        }

        StringBuilder sb = new StringBuilder("Historia clínica (simulada):\n\n");
        hist.forEach((key, value) -> sb.append(key).append(" -> ").append(value).append("\n\n"));

        JTextArea area = new JTextArea(sb.toString());
        area.setColumns(80);
        area.setRows(20);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(area);
        sp.setPreferredSize(new java.awt.Dimension(700, 400));
        JOptionPane.showMessageDialog(null, sp, "Historia clínica (simulada)", JOptionPane.INFORMATION_MESSAGE);
    }
}
