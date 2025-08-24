/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.application.port.in;

/**
 *
 * @author Diego
 */
import app.domain.model.Order;
import app.domain.model.OrderType;

public interface CreateOrderUseCase {
    Order create(CreateOrderCommand command);

    final class CreateOrderCommand {
        public final String orderNumber;      // must be unique, <= 6 digits
        public final String patientIdNumber;
        public final String doctorIdNumber;   // doctor's idNumber
        public final String createdAtIso;     // yyyy-MM-dd
        public final OrderType orderType;

        public CreateOrderCommand(String orderNumber, String patientIdNumber,
                                  String doctorIdNumber, String createdAtIso,
                                  OrderType orderType) {
            this.orderNumber = orderNumber;
            this.patientIdNumber = patientIdNumber;
            this.doctorIdNumber = doctorIdNumber;
            this.createdAtIso = createdAtIso;
            this.orderType = orderType;
        }
    }
}
