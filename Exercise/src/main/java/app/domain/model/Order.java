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

public class Order {
    private String orderNumber;      // unique, max 6 digits (validación en app)
    private String patientIdNumber;
    private String doctorIdNumber;   // cédula del médico
    private LocalDate createdAt;
    private OrderType orderType;     // MEDICATION, PROCEDURE, DIAGNOSTIC

    public Order(){}

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getPatientIdNumber() { return patientIdNumber; }
    public void setPatientIdNumber(String patientIdNumber) { this.patientIdNumber = patientIdNumber; }

    public String getDoctorIdNumber() { return doctorIdNumber; }
    public void setDoctorIdNumber(String doctorIdNumber) { this.doctorIdNumber = doctorIdNumber; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public OrderType getOrderType() { return orderType; }
    public void setOrderType(OrderType orderType) { this.orderType = orderType; }
}
