package app.domain.services;

import app.domain.entities.Nurse;
import app.domain.repositories.NurseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    // Registrar nueva enfermera
    public Nurse registerNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    // Listar todas las enfermeras
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    // Buscar enfermera por número de documento
    public Nurse findByDocumentNumber(String documentNumber) {
        return nurseRepository.findByDocumentNumber(documentNumber).orElse(null);
    }

    // Verificar si ya existe licencia
    public boolean existsByLicense(String licenseNumber) {
        return nurseRepository.existsByLicenseNumber(licenseNumber);
    }
}
