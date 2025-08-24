/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.model;

/**
 *
 * @author Diego
 */
import java.time.LocalDate;
import java.util.Objects;

public class Patient {
    private String idNumber;      // cédula (única)
    private String username;      // único, máx 15, alfanumérico
    private String passwordHash;  // validado en capa de app
    private String fullName;
    private LocalDate birthDate;  // ≤ 150 años (validación en app)
    private String gender;        // "M", "F", "O"
    private String address;
    private String phone;         // 10 dígitos
    private String email;         // opcional

    private EmergencyContact emergencyContact; // max 1
    private InsurancePolicy insurancePolicy;   // max 1

    public Patient() {}

    // Getters/Setters (regla: nombres autoexplicativos)
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public EmergencyContact getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(EmergencyContact emergencyContact) { this.emergencyContact = emergencyContact; }

    public InsurancePolicy getInsurancePolicy() { return insurancePolicy; }
    public void setInsurancePolicy(InsurancePolicy insurancePolicy) { this.insurancePolicy = insurancePolicy; }

    @Override public boolean equals(Object o){ return o instanceof Patient p && Objects.equals(idNumber, p.idNumber); }
    @Override public int hashCode(){ return Objects.hash(idNumber); }
}
