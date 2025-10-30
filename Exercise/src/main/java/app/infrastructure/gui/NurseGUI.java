package app.infrastructure.gui;

import app.domain.entities.*;
import app.domain.services.*;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz gráfica (JOptionPane) para la gestión de enfermeras y registro clínico de pacientes.
 * Permite registrar visitas, signos vitales, medicamentos y procedimientos.
 */
@Component
public class NurseGUI {

    private final NurseService nurseService;
    private final PatientService patientService;
    private final MedicationService medicationService;
    private final ProcedureService procedureService;
    private final VitalSignService vitalSignService;
    private final ClinicalRecordService clinicalRecordService;
    private final NurseRecordService nurseRecordService; // 👈 nuevo servicio para visitas

    public NurseGUI(
            NurseService nurseService,
            PatientService patientService,
            MedicationService medicationService,
            ProcedureService procedureService,
            VitalSignService vitalSignService,
            ClinicalRecordService clinicalRecordService,
            NurseRecordService nurseRecordService
    ) {
        this.nurseService = nurseService;
        this.patientService = patientService;
        this.medicationService = medicationService;
        this.procedureService = procedureService;
        this.vitalSignService = vitalSignService;
        this.clinicalRecordService = clinicalRecordService;
        this.nurseRecordService = nurseRecordService;
    }

    // 🔹 Menú principal
    public void showMenu() {
        String[] options = {
                "Registrar enfermera",
                "Listar enfermeras",
                "Registrar visita de paciente",
                "Registrar signos vitales",
                "Registrar medicamento aplicado",
                "Registrar procedimiento realizado",
                "Consultar registros clínicos",
                "Volver"
        };

        // Crear lista visual
        JList<String> optionList = new JList<>(options);
        optionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        optionList.setVisibleRowCount(options.length);
        optionList.setFixedCellHeight(25);
        optionList.setFixedCellWidth(250);

        // Scroll para evitar que se salga de pantalla
        JScrollPane scrollPane = new JScrollPane(optionList);
        scrollPane.setPreferredSize(new java.awt.Dimension(350, 200));

        while (true) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    scrollPane,
                    "👩‍⚕️ Menú de Enfermería",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result != JOptionPane.OK_OPTION) return;

            int selected = optionList.getSelectedIndex();
            if (selected == -1) continue;

            switch (selected) {
                case 0 -> registerNurse();
                case 1 -> listNurses();
                case 2 -> registerVisit();
                case 3 -> registerVitalSigns();
                case 4 -> registerMedicationApplied();
                case 5 -> registerProcedurePerformed();
                case 6 -> viewClinicalRecords();
                case 7 -> { return; }
            }
        }
    }


    // 🧍‍♀️ Registrar enfermera
    private void registerNurse() {
        try {
            String fullName = JOptionPane.showInputDialog("Nombre completo de la enfermera:");
            if (fullName == null || fullName.isBlank()) return;

            String document = JOptionPane.showInputDialog("Número de documento:");
            if (document == null || document.isBlank()) return;

            String email = JOptionPane.showInputDialog("Correo electrónico:");
            String workArea = JOptionPane.showInputDialog("Área de trabajo:");
            String licenseNumber = JOptionPane.showInputDialog("Número de licencia profesional:");

            Nurse nurse = new Nurse();
            nurse.setFullName(fullName);
            nurse.setDocumentNumber(document);
            nurse.setEmail(email);
            nurse.setWorkArea(workArea);
            nurse.setLicenseNumber(licenseNumber);
            nurse.setActive(true);

            nurseService.registerNurse(nurse);
            JOptionPane.showMessageDialog(null, "✅ Enfermera registrada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    // 📋 Listar enfermeras
    private void listNurses() {
        List<Nurse> nurses = nurseService.getAllNurses();
        if (nurses.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay enfermeras registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("Lista de enfermeras:\n\n");
        nurses.forEach(n ->
                sb.append("ID: ").append(n.getId())
                        .append(" | Nombre: ").append(n.getFullName())
                        .append(" | Área: ").append(n.getWorkArea())
                        .append(" | Licencia: ").append(n.getLicenseNumber())
                        .append(" | Activa: ").append(n.isActive() ? "Sí" : "No")
                        .append("\n")
        );
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // 👩‍⚕️ Registrar visita de enfermería
    private void registerVisit() {
        String nurseDoc = JOptionPane.showInputDialog("Documento de la enfermera:");
        if (nurseDoc == null || nurseDoc.isBlank()) return;

        Nurse nurse = nurseService.findByDocumentNumber(nurseDoc);
        if (nurse == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Enfermera no encontrada.");
            return;
        }

        String patientDoc = JOptionPane.showInputDialog("Documento del paciente:");
        if (patientDoc == null || patientDoc.isBlank()) return;

        Patient patient = patientService.findByDocument(patientDoc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        String reason = JOptionPane.showInputDialog("Motivo de la visita:");
        String notes = JOptionPane.showInputDialog("Observaciones durante la atención:");

        try {
            NurseRecord record = new NurseRecord();
            record.setNurse(nurse);
            record.setPatient(patient);
            record.setVisitReason(reason);
            record.setObservations(notes);
            record.setVisitDate(LocalDateTime.now());

            nurseRecordService.save(record);
            JOptionPane.showMessageDialog(null, "✅ Visita registrada exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al registrar visita: " + e.getMessage());
        }
    }

    // ❤️ Registrar signos vitales
    private void registerVitalSigns() {
        String patientDoc = JOptionPane.showInputDialog("Cédula del paciente:");
        if (patientDoc == null || patientDoc.isBlank()) return;

        Patient patient = patientService.findByDocument(patientDoc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        try {
            double pressure = Double.parseDouble(JOptionPane.showInputDialog("Presión arterial (mmHg):"));
            double temperature = Double.parseDouble(JOptionPane.showInputDialog("Temperatura (°C):"));
            double pulse = Double.parseDouble(JOptionPane.showInputDialog("Pulso (lpm):"));
            double oxygen = Double.parseDouble(JOptionPane.showInputDialog("Oxígeno en sangre (%):"));

            VitalSign vital = new VitalSign();
            vital.setPatient(patient);
            vital.setBloodPressure(pressure);
            vital.setTemperature(temperature);
            vital.setPulse(pulse);
            vital.setOxygenLevel(oxygen);
            vital.setRecordedAt(LocalDateTime.now());

            vitalSignService.save(vital);
            JOptionPane.showMessageDialog(null, "✅ Signos vitales registrados correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    // 💊 Registrar medicamento aplicado
    private void registerMedicationApplied() {
        String patientDoc = JOptionPane.showInputDialog("Cédula del paciente:");
        if (patientDoc == null || patientDoc.isBlank()) return;

        Patient patient = patientService.findByDocument(patientDoc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        List<Medication> meds = medicationService.findAll();
        if (meds.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay medicamentos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("Medicamentos disponibles:\n");
        meds.forEach(m -> sb.append(m.getId()).append(" - ").append(m.getName()).append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString());

        try {
            Long medId = Long.parseLong(JOptionPane.showInputDialog("ID del medicamento aplicado:"));
            Medication med = meds.stream().filter(m -> m.getId().equals(medId)).findFirst().orElse(null);
            if (med == null) {
                JOptionPane.showMessageDialog(null, "❌ Medicamento no encontrado.");
                return;
            }

            String dose = JOptionPane.showInputDialog("Dosis aplicada:");
            String notes = JOptionPane.showInputDialog("Observaciones:");

            ClinicalRecord record = new ClinicalRecord();
            record.setPatient(patient);
            record.setMedication(med);
            record.setDose(dose);
            record.setObservation(notes);
            record.setRecordType("MEDICAMENTO");
            record.setCreatedAt(LocalDateTime.now());

            clinicalRecordService.save(record);
            JOptionPane.showMessageDialog(null, "✅ Medicamento registrado en historia clínica.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    // 🧪 Registrar procedimiento realizado
    private void registerProcedurePerformed() {
        String patientDoc = JOptionPane.showInputDialog("Cédula del paciente:");
        if (patientDoc == null || patientDoc.isBlank()) return;

        Patient patient = patientService.findByDocument(patientDoc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        List<Procedure> procs = procedureService.findAll();
        if (procs.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay procedimientos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("Procedimientos disponibles:\n");
        procs.forEach(p -> sb.append(p.getId()).append(" - ").append(p.getName()).append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString());

        try {
            Long procId = Long.parseLong(JOptionPane.showInputDialog("ID del procedimiento realizado:"));
            Procedure proc = procs.stream().filter(p -> p.getId().equals(procId)).findFirst().orElse(null);
            if (proc == null) {
                JOptionPane.showMessageDialog(null, "❌ Procedimiento no encontrado.");
                return;
            }

            String notes = JOptionPane.showInputDialog("Observaciones durante la atención:");

            ClinicalRecord record = new ClinicalRecord();
            record.setPatient(patient);
            record.setProcedure(proc);
            record.setObservation(notes);
            record.setRecordType("PROCEDIMIENTO");
            record.setCreatedAt(LocalDateTime.now());

            clinicalRecordService.save(record);
            JOptionPane.showMessageDialog(null, "✅ Procedimiento registrado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    // 📄 Consultar registros clínicos del paciente
    private void viewClinicalRecords() {
        String doc = JOptionPane.showInputDialog("Cédula del paciente:");
        if (doc == null || doc.isBlank()) return;

        Patient patient = patientService.findByDocument(doc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        List<ClinicalRecord> records = clinicalRecordService.findByPatient(patient);
        if (records.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay registros clínicos para este paciente.");
            return;
        }

        StringBuilder sb = new StringBuilder("📋 HISTORIAL CLÍNICO\n\n");
        for (ClinicalRecord r : records) {
            sb.append("- ").append(r.getRecordType())
                    .append(" | Fecha: ").append(r.getCreatedAt())
                    .append("\n   Observaciones: ").append(r.getObservation())
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
