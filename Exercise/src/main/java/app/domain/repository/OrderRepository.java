/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.repository;

/**
 *
 * @author Diego
 */

import app.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findByOrderNumber(String orderNumber);
    Order save(Order order);
    boolean existsAnyItemWithSameOrderAndItemNumber(String orderNumber, int itemNumber);
    List<Order> findByPatient(String patientIdNumber);
}
