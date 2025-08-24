/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.application.service;

/**
 *
 * @author Diego
 */
import app.application.port.in.AssignInsurancePolicyUseCase;
import app.domain.model.Patient;
import app.domain.repository.PatientRepository;

public class AssignInsurancePolicyService implements AssignInsurancePolicyUseCase {

    private final PatientRepository patientRepository;

    public AssignInsurancePolicyService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient assign(AssignPolicyCommand cmd) {
        var patient = patientRepository.findByIdNumber(cmd.patientIdNumber)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        // TODO: validate active flag and endDate (must be >= today)
        patient.setInsurancePolicy(cmd.policy);

        return patientRepository.save(patient);
    }
}