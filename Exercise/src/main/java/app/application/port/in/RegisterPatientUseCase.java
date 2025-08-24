/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.application.port.in;

/**
 *
 * @author Diego
 */

import app.domain.model.Patient;
import app.domain.model.EmergencyContact;

public interface RegisterPatientUseCase {
    Patient register(RegisterPatientCommand command);

    final class RegisterPatientCommand {
        public final String idNumber;
        public final String username;
        public final String rawPassword;
        public final String fullName;
        public final String birthDateIso; // yyyy-MM-dd
        public final String gender;
        public final String address;
        public final String phone;
        public final String email;
        public final EmergencyContact emergencyContact;

        public RegisterPatientCommand(
                String idNumber, String username, String rawPassword, String fullName,
                String birthDateIso, String gender, String address, String phone,
                String email, EmergencyContact emergencyContact) {
            this.idNumber = idNumber;
            this.username = username;
            this.rawPassword = rawPassword;
            this.fullName = fullName;
            this.birthDateIso = birthDateIso;
            this.gender = gender;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.emergencyContact = emergencyContact;
        }
    }
}
