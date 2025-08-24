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

public class MedicationOrderItem {
    private String orderNumber;
    private int itemNumber;      // empieza en 1, único por (orderNumber,itemNumber)
    private String medicationName;
    private String dose;
    private String treatmentDuration;
    private BigDecimal cost;

    public MedicationOrderItem(){}

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public int getItemNumber() { return itemNumber; }
    public void setItemNumber(int itemNumber) { this.itemNumber = itemNumber; }

    public String getMedicationName() { return medicationName; }
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }

    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }

    public String getTreatmentDuration() { return treatmentDuration; }
    public void setTreatmentDuration(String treatmentDuration) { this.treatmentDuration = treatmentDuration; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}