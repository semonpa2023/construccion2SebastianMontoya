package app.domain.services;

import app.domain.entities.MedicalOrder;
import app.domain.entities.OrderItem;
import app.domain.enums.ItemType;
import app.domain.repositories.MedicalOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

/**
 * Lógica para órdenes médicas (reglas incluidas).
 */
@Service
public class OrderService {

    private final MedicalOrderRepository orderRepository;
    private final SecureRandom rnd = new SecureRandom();

    public OrderService(MedicalOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Generar número de orden único de 6 dígitos
    private String generateUniqueOrderNumber() {
        String num;
        do {
            int v = 100000 + rnd.nextInt(900000);
            num = String.valueOf(v);
        } while (orderRepository.existsByOrderNumber(num));
        return num;
    }

    /**
     * Crea una orden vacía y devuelve la entidad persistida.
     */
    public MedicalOrder createOrder(String patientDocument, String doctorDocument) {
        MedicalOrder order = new MedicalOrder();
        order.setOrderNumber(generateUniqueOrderNumber());
        order.setPatientDocument(patientDocument);
        order.setDoctorDocument(doctorDocument);
        return orderRepository.save(order);
    }

    /**
     * Añade un ítem a una orden validando reglas:
     * - Si la orden ya tiene un item DIAGNOSTIC_AID, no se permiten MEDICATION o PROCEDURE en la misma orden.
     * - Si se intenta agregar DIAGNOSTIC_AID en orden que ya tiene MEDICATION/PROCEDURE, se rechaza.
     */
    @Transactional
    public OrderItem addItem(MedicalOrder order, OrderItem item) {
        // recuperar estado actual
        List<OrderItem> existing = order.getItems();

        boolean hasDiag = existing.stream().anyMatch(i -> i.getType() == ItemType.DIAGNOSTIC_AID);
        boolean hasMedOrProc = existing.stream().anyMatch(i -> i.getType() == ItemType.MEDICATION || i.getType() == ItemType.PROCEDURE);

        if (item.getType() == ItemType.DIAGNOSTIC_AID && hasMedOrProc) {
            throw new IllegalStateException("No se puede agregar ayuda diagnóstica a una orden que ya contiene medicamentos/procedimientos.");
        }
        if ((item.getType() == ItemType.MEDICATION || item.getType() == ItemType.PROCEDURE) && hasDiag) {
            throw new IllegalStateException("No se pueden agregar medicamentos/procedimientos a una orden que ya contiene ayudas diagnósticas.");
        }

        order.addItem(item);
        // costo nulo? puede calcularse externamente
        if (item.getCost() == null) item.setCost(BigDecimal.ZERO);
        MedicalOrder saved = orderRepository.save(order); // Cascade guarda items
        // devolver el último item guardado (id asignado)
        return saved.getItems().stream().filter(i -> i.getItemCode().equals(item.getItemCode()) && i.getItemName().equals(item.getItemName())).findFirst().orElse(item);
    }

    public Optional<MedicalOrder> findByOrderNumber(String number) {
        return orderRepository.findByOrderNumber(number);
    }

    public List<MedicalOrder> findAll() { return orderRepository.findAll(); }
}
