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

public class ProcedureOrderItem {
    private String orderNumber;
    private int itemNumber;
    private String procedureName;
    private int repetitions;
    private String frequency;
    private boolean requiresSpecialist;
    private String specialtyId; // optional
    private BigDecimal cost;

    public ProcedureOrderItem(){}

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public int getItemNumber() { return itemNumber; }
    public void setItemNumber(int itemNumber) { this.itemNumber = itemNumber; }

    public String getProcedureName() { return procedureName; }
    public void setProcedureName(String procedureName) { this.procedureName = procedureName; }

    public int getRepetitions() { return repetitions; }
    public void setRepetitions(int repetitions) { this.repetitions = repetitions; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public boolean isRequiresSpecialist() { return requiresSpecialist; }
    public void setRequiresSpecialist(boolean requiresSpecialist) { this.requiresSpecialist = requiresSpecialist; }

    public String getSpecialtyId() { return specialtyId; }
    public void setSpecialtyId(String specialtyId) { this.specialtyId = specialtyId; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}
