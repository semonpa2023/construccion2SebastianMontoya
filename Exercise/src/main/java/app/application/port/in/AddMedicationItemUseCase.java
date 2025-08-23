/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.application.port.in;

/**
 *
 * @author Diego
 */
public interface AddMedicationItemUseCase {
    void add(AddMedicationItemCommand command);

    final class AddMedicationItemCommand {
        public final String orderNumber;
        public final int itemNumber;
        public final String medicationName;
        public final String dose;
        public final String treatmentDuration;

        public AddMedicationItemCommand(String orderNumber, int itemNumber,
                                        String medicationName, String dose,
                                        String treatmentDuration) {
            this.orderNumber = orderNumber;
            this.itemNumber = itemNumber;
            this.medicationName = medicationName;
            this.dose = dose;
            this.treatmentDuration = treatmentDuration;
        }
    }
}
