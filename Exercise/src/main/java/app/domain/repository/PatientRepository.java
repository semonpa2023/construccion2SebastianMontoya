/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.repository;

/**
 *
 * @author Diego
 */
import app.domain.model.Patient;
import java.util.Optional;

public interface PatientRepository {
    Optional<Patient> findByIdNumber(String idNumber);
    Optional<Patient> findByUsername(String username);
    Patient save(Patient patient); // crea/actualiza
}