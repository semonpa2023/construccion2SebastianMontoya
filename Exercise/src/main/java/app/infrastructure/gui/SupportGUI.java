package app.infrastructure.gui;

import app.domain.entities.*;
import app.domain.services.*;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class SupportGUI {

    private final MedicationService medicationService;
    private final ProcedureService procedureService;
    private final DiagnosticAidService diagnosticAidService;

    public SupportGUI(MedicationService medicationService,
                      ProcedureService procedureService,
                      DiagnosticAidService diagnosticAidService) {
        this.medicationService = medicationService;
        this.procedureService = procedureService;
        this.diagnosticAidService = diagnosticAidService;
    }

    public void showMenu() {
        String[] options = {
                "Gestionar medicamentos",
                "Gestionar procedimientos",
                "Gestionar ayudas diagnósticas",
                "Ver logs / Reiniciar módulos (simulado)",
                "Volver"
        };

        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null, "Menú de Soporte de Información",
                    "Soporte del Sistema",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (choice < 0 || choice == 4) return;

            switch (choice) {
                case 0 -> manageMedications();
                case 1 -> manageProcedures();
                case 2 -> manageDiagnosticAids();
                case 3 -> JOptionPane.showMessageDialog(null, "✅ Logs limpiados y módulos reiniciados (simulado).");
            }
        }
    }

    // -------------------- MEDICAMENTOS --------------------
    private void manageMedications() {
        String[] options = {"Ver", "Crear", "Editar", "Eliminar", "Volver"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Gestión de Medicamentos", "Medicamentos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choice < 0 || choice == 4) return;

            switch (choice) {
                case 0 -> listEntities(medicationService.findAll());
                case 1 -> createMedication();
                case 2 -> editMedication();
                case 3 -> deleteEntity("medicamento", id -> medicationService.delete(id));
            }
        }
    }

    private void createMedication() {
        try {
            String name = JOptionPane.showInputDialog("Nombre del medicamento:");
            if (name == null || name.isBlank()) return;

            String desc = JOptionPane.showInputDialog("Descripción:");
            if (desc == null) return;

            String qtyStr = JOptionPane.showInputDialog("Cantidad:");
            if (qtyStr == null || qtyStr.isBlank()) return;
            int qty = Integer.parseInt(qtyStr);

            String priceStr = JOptionPane.showInputDialog("Precio:");
            if (priceStr == null || priceStr.isBlank()) return;
            double price = Double.parseDouble(priceStr);

            Medication m = new Medication();
            m.setName(name);
            m.setDescription(desc);
            m.setQuantity(qty);
            m.setPrice(price);
            medicationService.save(m);
            JOptionPane.showMessageDialog(null, "✅ Medicamento registrado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Valor numérico inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al crear medicamento: " + e.getMessage());
        }
    }

    private void editMedication() {
        try {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del medicamento a editar:");
            if (idStr == null || idStr.isBlank()) return;
            Long id = Long.parseLong(idStr);

            Medication med = medicationService.findAll().stream()
                    .filter(m -> m.getId().equals(id)).findFirst().orElse(null);

            if (med == null) {
                JOptionPane.showMessageDialog(null, "⚠️ Medicamento no encontrado.");
                return;
            }

            String newName = JOptionPane.showInputDialog("Nuevo nombre:", med.getName());
            if (newName != null && !newName.isBlank()) med.setName(newName);

            String newDesc = JOptionPane.showInputDialog("Nueva descripción:", med.getDescription());
            if (newDesc != null) med.setDescription(newDesc);

            String newQty = JOptionPane.showInputDialog("Nueva cantidad:", med.getQuantity());
            if (newQty != null && !newQty.isBlank()) med.setQuantity(Integer.parseInt(newQty));

            String newPrice = JOptionPane.showInputDialog("Nuevo precio:", med.getPrice());
            if (newPrice != null && !newPrice.isBlank()) med.setPrice(Double.parseDouble(newPrice));

            medicationService.save(med);
            JOptionPane.showMessageDialog(null, "✅ Medicamento actualizado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al editar medicamento: " + e.getMessage());
        }
    }

    // -------------------- PROCEDIMIENTOS --------------------
    private void manageProcedures() {
        String[] options = {"Ver", "Crear", "Eliminar", "Volver"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Gestión de Procedimientos", "Procedimientos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choice < 0 || choice == 3) return;

            switch (choice) {
                case 0 -> listEntities(procedureService.findAll());
                case 1 -> createProcedure();
                case 2 -> deleteEntity("procedimiento", id -> procedureService.delete(id));
            }
        }
    }

    private void createProcedure() {
        try {
            String name = JOptionPane.showInputDialog("Nombre del procedimiento:");
            if (name == null || name.isBlank()) return;

            String desc = JOptionPane.showInputDialog("Descripción:");
            if (desc == null) return;

            String costStr = JOptionPane.showInputDialog("Costo:");
            if (costStr == null || costStr.isBlank()) return;
            double cost = Double.parseDouble(costStr);

            Procedure p = new Procedure();
            p.setName(name);
            p.setDescription(desc);
            p.setCost(cost);
            procedureService.save(p);
            JOptionPane.showMessageDialog(null, "✅ Procedimiento registrado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ El costo debe ser numérico.");
        }
    }

    // -------------------- AYUDAS DIAGNÓSTICAS --------------------
    private void manageDiagnosticAids() {
        String[] options = {"Ver", "Crear", "Eliminar", "Volver"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Gestión de Ayudas Diagnósticas", "Ayudas Diagnósticas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choice < 0 || choice == 3) return;

            switch (choice) {
                case 0 -> listEntities(diagnosticAidService.findAll());
                case 1 -> createDiagnosticAid();
                case 2 -> deleteEntity("ayuda diagnóstica", id -> diagnosticAidService.delete(id));
            }
        }
    }

    private void createDiagnosticAid() {
        try {
            String name = JOptionPane.showInputDialog("Nombre del examen:");
            if (name == null || name.isBlank()) return;

            String desc = JOptionPane.showInputDialog("Descripción:");
            if (desc == null) return;

            String costStr = JOptionPane.showInputDialog("Costo:");
            if (costStr == null || costStr.isBlank()) return;
            double cost = Double.parseDouble(costStr);

            DiagnosticAid d = new DiagnosticAid();
            d.setName(name);
            d.setDescription(desc);
            d.setCost(cost);
            diagnosticAidService.save(d);
            JOptionPane.showMessageDialog(null, "✅ Ayuda diagnóstica registrada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ El costo debe ser numérico.");
        }
    }

    // -------------------- UTILIDADES --------------------
    private <T> void listEntities(List<T> list) {
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay registros disponibles.");
            return;
        }
        StringBuilder sb = new StringBuilder("📋 Lista de registros:\n\n");
        list.forEach(e -> sb.append(e.toString()).append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void deleteEntity(String type, java.util.function.Consumer<Long> deleteAction) {
        try {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del " + type + " a eliminar:");
            if (idStr == null || idStr.isBlank()) return;
            Long id = Long.parseLong(idStr);
            deleteAction.accept(id);
            JOptionPane.showMessageDialog(null, "✅ " + capitalize(type) + " eliminado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ El ID debe ser un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar " + type + ": " + e.getMessage());
        }
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
