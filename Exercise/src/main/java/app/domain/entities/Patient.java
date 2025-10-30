package app.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que representa a un paciente dentro del sistema clínico.
 * Contiene la información personal, de contacto y asociaciones con seguro y contacto de emergencia.
 */
@Entity
public class Patient {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del paciente
    private String fullName;

    // Documento de identificación único
    private String documentNumber;

    // Fecha de nacimiento
    private LocalDate birthDate;

    // Género del paciente (M, F, Otro)
    private String gender;

    // Dirección de residencia
    private String address;

    // Número telefónico de contacto
    private String phone;

    // Correo electrónico del paciente
    private String email;

    // Nombre de usuario para autenticación
    private String username;

    // Contraseña del paciente
    private String password;

    // Relación uno a uno con la entidad Insurance (seguro médico)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id") // Llave foránea hacia Insurance
    private Insurance insurance;

    // Relación uno a uno con la entidad EmergencyContact
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emergency_contact_id") // Llave foránea hacia EmergencyContact
    private EmergencyContact emergencyContact;

    // Getters y Setters
    public Long getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

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

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Insurance getInsurance() { return insurance; }
    public void setInsurance(Insurance insurance) { this.insurance = insurance; }

    public EmergencyContact getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(EmergencyContact emergencyContact) { this.emergencyContact = emergencyContact; }
}
