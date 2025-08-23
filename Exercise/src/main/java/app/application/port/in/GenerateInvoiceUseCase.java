/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.application.port.in;

/**
 *
 * @author Diego
 */
import app.domain.model.Invoice;

public interface GenerateInvoiceUseCase {
    Invoice generate(GenerateInvoiceCommand command);

    final class GenerateInvoiceCommand {
        public final String patientIdNumber;
        public final String doctorName;
        public final String invoiceNumber; // generated upstream or here

        public GenerateInvoiceCommand(String patientIdNumber, String doctorName, String invoiceNumber) {
            this.patientIdNumber = patientIdNumber;
            this.doctorName = doctorName;
            this.invoiceNumber = invoiceNumber;
        }
    }
}
