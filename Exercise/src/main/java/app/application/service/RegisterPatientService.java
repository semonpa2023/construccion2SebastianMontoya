/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.application.service;

/**
 *
 * @author Diego
 */

import app.application.port.in.RegisterPatientUseCase;
import app.domain.model.Patient;
import app.domain.repository.PatientRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class RegisterPatientService implements RegisterPatientUseCase {

    private final PatientRepository patientRepository;

    public RegisterPatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient register(RegisterPatientCommand cmd) {
        // TODO: validations per rules: username unique, <=15, alphanumeric, phone 10 digits,
        // email format, birthDate <= 150 years, idNumber unique, password rules, etc.
        if (cmd == null) throw new IllegalArgumentException("Command is required");

        if (patientRepository.findByIdNumber(cmd.idNumber).isPresent())
            throw new IllegalArgumentException("Patient already exists");

        if (patientRepository.findByUsername(cmd.username).isPresent())
            throw new IllegalArgumentException("Username already exists");

        LocalDate birth = LocalDate.parse(cmd.birthDateIso);
        if (Period.between(birth, LocalDate.now()).getYears() > 150)
            throw new IllegalArgumentException("Invalid birth date (>150 years)");

        if (!Pattern.matches("^[a-zA-Z0-9]{1,15}$", cmd.username))
            throw new IllegalArgumentException("Username must be alphanumeric and <=15 chars");

        if (!Pattern.matches("^\\d{10}$", cmd.phone))
            throw new IllegalArgumentException("Phone must have 10 digits");

        Patient p = new Patient();
        p.setIdNumber(cmd.idNumber);
        p.setUsername(cmd.username);
        p.setPasswordHash(hash(cmd.rawPassword)); // TODO: use a real password encoder
        p.setFullName(cmd.fullName);
        p.setBirthDate(birth);
        p.setGender(cmd.gender);
        p.setAddress(cmd.address);
        p.setPhone(cmd.phone);
        p.setEmail(cmd.email);
        p.setEmergencyContact(cmd.emergencyContact);
        // policy not set here

        return patientRepository.save(p);
    }

    private String hash(String raw) {
        // NOTE: replace with BCrypt/Argon2 in infra
        return Integer.toHexString(raw.hashCode());
    }
}
