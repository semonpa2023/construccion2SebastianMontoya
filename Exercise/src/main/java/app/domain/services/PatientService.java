package app.domain.services;

import app.domain.entities.Patient;
import app.domain.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar la lógica de pacientes.
 * Comentarios en español, código en inglés.
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // -------------------- REGISTRAR PACIENTE --------------------
    public String registerPatient(Patient patient) {
        Optional<Patient> existing = patientRepository.findByDocumentNumber(patient.getDocumentNumber());
        if (existing.isPresent()) return "Ya existe un paciente con esa cédula.";

        patientRepository.save(patient);
        return "Paciente registrado correctamente.";
    }

    // -------------------- BUSCAR POR CÉDULA --------------------
    public Patient findByDocument(String documentNumber) {
        return patientRepository.findByDocumentNumber(documentNumber).orElse(null);
    }

    // -------------------- LISTAR TODOS --------------------
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // -------------------- ACTUALIZAR PACIENTE --------------------
    public String updatePatient(Patient patient) {
        Optional<Patient> existing = patientRepository.findByDocumentNumber(patient.getDocumentNumber());
        if (existing.isEmpty()) return "No se encontró un paciente con esa cédula.";

        Patient existingPatient = existing.get();
        existingPatient.setFullName(patient.getFullName());
        existingPatient.setBirthDate(patient.getBirthDate());
        existingPatient.setGender(patient.getGender());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setPhone(patient.getPhone());
        existingPatient.setEmail(patient.getEmail());

        patientRepository.save(existingPatient);
        return "Datos del paciente actualizados correctamente.";
    }

    // -------------------- ELIMINAR PACIENTE --------------------
    public String deleteByDocument(String documentNumber) {
        Optional<Patient> existing = patientRepository.findByDocumentNumber(documentNumber);
        if (existing.isEmpty()) return "Paciente no encontrado.";

        patientRepository.delete(existing.get());
        return "Paciente eliminado correctamente.";
    }
}
