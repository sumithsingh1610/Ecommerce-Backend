package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.Payment;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.PaymentRepository;
import com.ecommerce.backend.response.ApiResponse;
import com.ecommerce.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // ✅ Make a new payment
    @Override
    public ApiResponse makePayment(Long orderId, String paymentMethod) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        // Create payment record
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentStatus("SUCCESS"); // simulate successful payment

        // Save payment
        paymentRepository.save(payment);

        // Update order status
        order.setPaymentStatus("PAID");
        orderRepository.save(order);

        return new ApiResponse(true, "Payment successful for Order ID: " + orderId);
    }

    // ✅ Get payment details by ID
    @Override
    public Payment getPaymentDetails(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }
}
