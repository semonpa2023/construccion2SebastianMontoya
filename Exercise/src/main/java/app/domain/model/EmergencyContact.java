/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.model;

/**
 *
 * @author Diego
 */

public class EmergencyContact {
    private String firstName;
    private String lastName;
    private String relationToPatient;
    private String phone; // 10 digits

    public EmergencyContact() {}

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRelationToPatient() { return relationToPatient; }
    public void setRelationToPatient(String relationToPatient) { this.relationToPatient = relationToPatient; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}