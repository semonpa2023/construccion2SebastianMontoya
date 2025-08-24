/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.application.service;

/**
 *
 * @author Diego
 */

import app.application.port.in.GenerateInvoiceUseCase;
import app.domain.model.Invoice;
import app.domain.model.Patient;
import app.domain.repository.InvoiceRepository;
import app.domain.repository.PatientRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GenerateInvoiceService implements GenerateInvoiceUseCase {

    private final PatientRepository patientRepository;
    private final InvoiceRepository invoiceRepository;

    public GenerateInvoiceService(PatientRepository patientRepository,
                                  InvoiceRepository invoiceRepository) {
        this.patientRepository = patientRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice generate(GenerateInvoiceCommand cmd) {
        Patient p = patientRepository.findByIdNumber(cmd.patientIdNumber)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        Invoice inv = new Invoice();
        inv.setInvoiceNumber(cmd.invoiceNumber);
        inv.setPatientIdNumber(p.getIdNumber());
        inv.setDoctorName(cmd.doctorName);

        if (p.getInsurancePolicy() != null) {
            var pol = p.getInsurancePolicy();
            inv.setInsuranceProviderName(pol.getProviderName());
            inv.setPolicyNumber(pol.getPolicyNumber());
            inv.setPolicyEndDate(pol.getEndDate());
            int daysLeft = pol.getEndDate() != null
                    ? (int) java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), pol.getEndDate())
                    : 0;
            inv.setPolicyDaysLeft(daysLeft);
            // Simple copay rule (placeholder):
            inv.setCopayAmount(BigDecimal.valueOf(pol.isActive() ? 50_000 : 0));
        } else {
            inv.setCopayAmount(BigDecimal.ZERO);
        }

        // TODO: compute insurerAmount, patientAmount, totalAmount based on orders/items
        inv.setInsurerAmount(BigDecimal.ZERO);
        inv.setPatientAmount(BigDecimal.ZERO);
        inv.setTotalAmount(inv.getCopayAmount());

        return invoiceRepository.save(inv);
    }
}