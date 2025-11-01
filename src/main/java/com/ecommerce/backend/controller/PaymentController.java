package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Payment;
import com.ecommerce.backend.response.ApiResponse;
import com.ecommerce.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // ✅ 1. Make Payment
    @PostMapping("/make/{orderId}")
    public ResponseEntity<ApiResponse> makePayment(
            @PathVariable Long orderId,
            @RequestParam String method
    ) {
        ApiResponse response = paymentService.makePayment(orderId, method);
        return ResponseEntity.ok(response);
    }

    // ✅ 2. Get Payment Details
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentDetails(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentDetails(paymentId);
        return ResponseEntity.ok(payment);
    }
}
