package app.infrastructure.gui;

import app.domain.entities.*;
import app.domain.services.*;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Interfaz gráfica administrativa para la gestión de pacientes, contactos de emergencia,
 * seguros médicos y facturación.
 */
@Component
public class AdministrativeGUI {

    private final PatientService patientService;
    private final EmergencyContactService contactService;
    private final InsuranceService insuranceService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AdministrativeGUI(
            PatientService patientService,
            EmergencyContactService contactService,
            InsuranceService insuranceService
    ) {
        this.patientService = patientService;
        this.contactService = contactService;
        this.insuranceService = insuranceService;
    }

    /**
     * Muestra el menú principal de administración.
     */
    public void showMenu() {
        String[] options = {
                "Registrar paciente",
                "Registrar contacto de emergencia",
                "Registrar seguro médico",
                "Generar factura",
                "Volver"
        };

        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null, "Menú Administrativo:",
                    "Administración de Pacientes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (choice < 0 || choice == 4) return;

            switch (choice) {
                case 0 -> registerPatient();
                case 1 -> registerEmergencyContact();
                case 2 -> registerInsurance();
                case 3 -> generateInvoice();
            }
        }
    }

    // -------------------- REGISTRO DE PACIENTE --------------------
    private void registerPatient() {
        try {
            String fullName = JOptionPane.showInputDialog("Ingrese el nombre completo del paciente:");
            if (fullName == null || fullName.isBlank()) return;

            String document = JOptionPane.showInputDialog("Ingrese la cédula:");
            if (document == null || document.isBlank()) return;

            String gender = JOptionPane.showInputDialog("Ingrese el género (M/F/Otro):");
            if (gender == null || gender.isBlank()) return;

            String birth = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento (YYYY-MM-DD):");
            if (birth == null || birth.isBlank()) return;

            String address = JOptionPane.showInputDialog("Ingrese la dirección:");
            String phone = JOptionPane.showInputDialog("Ingrese el teléfono (10 dígitos):");
            String email = JOptionPane.showInputDialog("Ingrese el correo electrónico:");
            String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
            String password = JOptionPane.showInputDialog("Ingrese la contraseña:");

            Patient patient = new Patient();
            patient.setFullName(fullName);
            patient.setDocumentNumber(document);
            patient.setGender(gender);
            patient.setBirthDate(LocalDate.parse(birth));
            patient.setAddress(address);
            patient.setPhone(phone);
            patient.setEmail(email);
            patient.setUsername(username);
            patient.setPassword(password);

            // Preguntar si desea añadir seguro
            int addInsurance = JOptionPane.showConfirmDialog(null, "¿Desea registrar seguro médico?", "Seguro", JOptionPane.YES_NO_OPTION);
            if (addInsurance == JOptionPane.YES_OPTION) {
                Insurance insurance = new Insurance();
                insurance.setCompanyName(JOptionPane.showInputDialog("Nombre de la compañía:"));
                insurance.setPolicyNumber(JOptionPane.showInputDialog("Número de póliza:"));
                insurance.setActive(JOptionPane.showConfirmDialog(null, "¿Está activa la póliza?", "Estado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
                insurance.setValidUntil(LocalDate.parse(JOptionPane.showInputDialog("Fecha de vigencia (YYYY-MM-DD):")));
                patient.setInsurance(insurance);
            }

            String result = patientService.registerPatient(patient);
            JOptionPane.showMessageDialog(null, result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar paciente: " + e.getMessage());
        }
    }

    // -------------------- CONTACTO DE EMERGENCIA --------------------
    private void registerEmergencyContact() {
        String patientDoc = JOptionPane.showInputDialog("Ingrese la cédula del paciente:");
        if (patientDoc == null) return;

        Patient patient = patientService.findByDocument(patientDoc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        String name = JOptionPane.showInputDialog("Nombre del contacto:");
        String relation = JOptionPane.showInputDialog("Relación con el paciente:");
        String phone = JOptionPane.showInputDialog("Teléfono (10 dígitos):");

        EmergencyContact contact = new EmergencyContact();
        contact.setFullName(name);
        contact.setRelationship(relation);
        contact.setPhone(phone);
        contact.setPatient(patient);

        contactService.save(contact);
        JOptionPane.showMessageDialog(null, "✅ Contacto registrado.");
    }

    // -------------------- SEGURO MÉDICO --------------------
    private void registerInsurance() {
        String patientDoc = JOptionPane.showInputDialog("Ingrese la cédula del paciente:");
        if (patientDoc == null) return;

        Patient patient = patientService.findByDocument(patientDoc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        Insurance insurance = new Insurance();
        insurance.setCompanyName(JOptionPane.showInputDialog("Compañía de seguros:"));
        insurance.setPolicyNumber(JOptionPane.showInputDialog("Número de póliza:"));
        insurance.setActive(JOptionPane.showConfirmDialog(null, "¿Está activa la póliza?", "Estado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        insurance.setValidUntil(LocalDate.parse(JOptionPane.showInputDialog("Fecha de vigencia (YYYY-MM-DD):")));

        patient.setInsurance(insurance);
        insuranceService.save(insurance);
        patientService.registerPatient(patient);

        JOptionPane.showMessageDialog(null, "✅ Seguro médico registrado correctamente.");
    }

    // -------------------- FACTURACIÓN --------------------
    private void generateInvoice() {
        String patientDoc = JOptionPane.showInputDialog("Ingrese la cédula del paciente:");
        if (patientDoc == null) return;

        Patient patient = patientService.findByDocument(patientDoc);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "⚠️ Paciente no encontrado.");
            return;
        }

        Insurance insurance = patient.getInsurance();

        double total = 500000;
        double copay = 0;

        if (insurance != null && insurance.isActive()) {
            copay = Math.min(50000, total);
            total -= copay;
        }

        StringBuilder invoice = new StringBuilder("🧾 FACTURA CLÍNICA\n\n")
                .append("Paciente: ").append(patient.getFullName()).append("\n")
                .append("Cédula: ").append(patient.getDocumentNumber()).append("\n\n")
                .append("Seguro: ").append(insurance != null ? insurance.getCompanyName() : "Sin seguro").append("\n")
                .append("Póliza: ").append(insurance != null ? insurance.getPolicyNumber() : "N/A").append("\n")
                .append("Vigencia: ").append(insurance != null ? insurance.getValidUntil() : "N/A").append("\n\n")
                .append("Costo total: $").append(total + copay).append("\n")
                .append("Copago: $").append(copay).append("\n")
                .append("Cubierto por seguro: $").append(total).append("\n");

        JOptionPane.showMessageDialog(null, invoice.toString());
    }
}
