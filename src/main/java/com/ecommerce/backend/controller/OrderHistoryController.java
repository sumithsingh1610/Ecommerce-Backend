package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.Payment;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.PaymentRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class OrderHistoryController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    // ✅ Get all orders for a user
    @GetMapping("/orders/{email}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Order> orders = orderRepository.findByUser(user);
        return ResponseEntity.ok(orders);
    }

    // ✅ Get all payments for a user
    @GetMapping("/payments/{email}")
    public ResponseEntity<List<Payment>> getPaymentHistory(@PathVariable String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Payment> payments = paymentRepository.findByOrderUser(user);
        return ResponseEntity.ok(payments);
    }
}
