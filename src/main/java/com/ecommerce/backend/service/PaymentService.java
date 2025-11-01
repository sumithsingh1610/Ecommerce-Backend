package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Payment;
import com.ecommerce.backend.response.ApiResponse;

public interface PaymentService {

    // 👇 1️⃣ Make a new payment for an order
    ApiResponse makePayment(Long orderId, String paymentMethod);

    // 👇 2️⃣ Get payment details by ID
    Payment getPaymentDetails(Long paymentId);
}
