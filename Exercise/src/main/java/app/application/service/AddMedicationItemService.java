/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.application.service;

/**
 *
 * @author Diego
 */
import app.application.port.in.AddMedicationItemUseCase;
import app.domain.model.MedicationOrderItem;
import app.domain.repository.MedicationCatalogRepository;
import app.domain.repository.OrderRepository;

import java.math.BigDecimal;

public class AddMedicationItemService implements AddMedicationItemUseCase {

    private final OrderRepository orderRepository;
    private final MedicationCatalogRepository medicationCatalogRepository;

    public AddMedicationItemService(OrderRepository orderRepository,
                                    MedicationCatalogRepository medicationCatalogRepository) {
        this.orderRepository = orderRepository;
        this.medicationCatalogRepository = medicationCatalogRepository;
    }

    @Override
    public void add(AddMedicationItemCommand cmd) {
        var order = orderRepository.findByOrderNumber(cmd.orderNumber)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (orderRepository.existsAnyItemWithSameOrderAndItemNumber(cmd.orderNumber, cmd.itemNumber)) {
            throw new IllegalArgumentException("Duplicate item number for this order");
        }

        if (!medicationCatalogRepository.existsByName(cmd.medicationName)) {
            throw new IllegalArgumentException("Medication not found in catalog");
        }

        MedicationOrderItem item = new MedicationOrderItem();
        item.setOrderNumber(cmd.orderNumber);
        item.setItemNumber(cmd.itemNumber);
        item.setMedicationName(cmd.medicationName);
        item.setDose(cmd.dose);
        item.setTreatmentDuration(cmd.treatmentDuration);

        // set price from catalog if available
        BigDecimal price = medicationCatalogRepository.priceOf(cmd.medicationName)
                .orElse(BigDecimal.ZERO);
        item.setCost(price);

        // NOTE: persistence of items can be handled by an ItemRepository
        // or via Order aggregate in infra layer. For now, we rely on infra.
        // TODO: orderRepository.saveOrderItem(item); (define in ports.out if needed)
    }
}
