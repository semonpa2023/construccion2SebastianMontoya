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

public class DiagnosticOrderItem {
    private String orderNumber;
    private int itemNumber;
    private String diagnosticName;
    private int quantity;
    private boolean requiresSpecialist;
    private String specialtyId; // optional
    private BigDecimal cost;

    public DiagnosticOrderItem(){}

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public int getItemNumber() { return itemNumber; }
    public void setItemNumber(int itemNumber) { this.itemNumber = itemNumber; }

    public String getDiagnosticName() { return diagnosticName; }
    public void setDiagnosticName(String diagnosticName) { this.diagnosticName = diagnosticName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isRequiresSpecialist() { return requiresSpecialist; }
    public void setRequiresSpecialist(boolean requiresSpecialist) { this.requiresSpecialist = requiresSpecialist; }

    public String getSpecialtyId() { return specialtyId; }
    public void setSpecialtyId(String specialtyId) { this.specialtyId = specialtyId; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}
