package app.application.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Diego
 */

import app.application.port.in.CreateOrderUseCase;
import app.domain.model.Order;
import app.domain.model.OrderType;
import app.domain.repository.OrderRepository;
import app.domain.repository.PatientRepository;
import app.domain.repository.StaffRepository;

import java.time.LocalDate;

public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;

    public CreateOrderService(OrderRepository orderRepository,
                              PatientRepository patientRepository,
                              StaffRepository staffRepository) {
        this.orderRepository = orderRepository;
        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public Order create(CreateOrderCommand cmd) {
        // Basic validations
        if (orderRepository.findByOrderNumber(cmd.orderNumber).isPresent())
            throw new IllegalArgumentException("Order number already exists");
        patientRepository.findByIdNumber(cmd.patientIdNumber)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        staffRepository.findByIdNumber(cmd.doctorIdNumber)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        if (cmd.orderType == null || cmd.orderType == OrderType.DIAGNOSTIC && cmd.doctorIdNumber == null) {
            // example rule, adjust as needed
        }

        Order order = new Order();
        order.setOrderNumber(cmd.orderNumber);
        order.setPatientIdNumber(cmd.patientIdNumber);
        order.setDoctorIdNumber(cmd.doctorIdNumber);
        order.setCreatedAt(LocalDate.parse(cmd.createdAtIso));
        order.setOrderType(cmd.orderType);

        return orderRepository.save(order);
    }
}