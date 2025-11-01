package com.ecommerce.backend.controller;

import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.*;
import com.ecommerce.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final AddressRepository addressRepository; // ✅ added for shipping address

    // ✅ Checkout API (with address)
    @PostMapping("/{email}/{addressId}")
    public ResponseEntity<String> checkout(
            @PathVariable String email,
            @PathVariable Long addressId
    ) {
        // 1️⃣ Find user
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 2️⃣ Find address
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        // 3️⃣ Get cart for user
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        if (cartItems.isEmpty()) {
            throw new ResourceNotFoundException("Cart is empty, cannot checkout");
        }

        // 4️⃣ Calculate total amount
        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // 5️⃣ Create new Order
        Order order = new Order();
        order.setUser(user);
        order.setItems(cartItems);
        order.setAddress(address); // ✅ link address
        order.setTotalAmount(totalAmount);
        order.setPaymentStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        // 6️⃣ Create new Payment
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod("UPI");
        payment.setPaymentStatus("SUCCESS");
        payment.setAmount(totalAmount);
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);

        // 7️⃣ Clear cart items
        cartItemRepository.deleteAll(cartItems);

        return ResponseEntity.ok("✅ Checkout successful! Order ID: " + order.getId());
    }
}
