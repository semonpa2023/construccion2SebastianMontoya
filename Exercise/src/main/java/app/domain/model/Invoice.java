/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.model;

/**
 *
 * @author Diego
 */

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {
    private String invoiceNumber;
    private String patientIdNumber;
    private String doctorName;
    private String insuranceProviderName;
    private String policyNumber;
    private int policyDaysLeft;
    private LocalDate policyEndDate;
    private BigDecimal copayAmount;          // reglas: 50k con póliza activa, tope 1'000.000/año
    private BigDecimal insurerAmount;
    private BigDecimal patientAmount;        // detalle completo del cobro
    private BigDecimal totalAmount;

    public Invoice(){}

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getPatientIdNumber() { return patientIdNumber; }
    public void setPatientIdNumber(String patientIdNumber) { this.patientIdNumber = patientIdNumber; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getInsuranceProviderName() { return insuranceProviderName; }
    public void setInsuranceProviderName(String insuranceProviderName) { this.insuranceProviderName = insuranceProviderName; }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public int getPolicyDaysLeft() { return policyDaysLeft; }
    public void setPolicyDaysLeft(int policyDaysLeft) { this.policyDaysLeft = policyDaysLeft; }

    public LocalDate getPolicyEndDate() { return policyEndDate; }
    public void setPolicyEndDate(LocalDate policyEndDate) { this.policyEndDate = policyEndDate; }

    public BigDecimal getCopayAmount() { return copayAmount; }
    public void setCopayAmount(BigDecimal copayAmount) { this.copayAmount = copayAmount; }

    public BigDecimal getInsurerAmount() { return insurerAmount; }
    public void setInsurerAmount(BigDecimal insurerAmount) { this.insurerAmount = insurerAmount; }

    public BigDecimal getPatientAmount() { return patientAmount; }
    public void setPatientAmount(BigDecimal patientAmount) { this.patientAmount = patientAmount; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
