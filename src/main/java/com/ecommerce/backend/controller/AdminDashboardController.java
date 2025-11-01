package com.ecommerce.backend.controller;

import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    // 📊 Get Admin Dashboard Summary
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {

        long totalUsers = userRepository.count();
        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();

        double totalRevenue = orderRepository.findAll().stream()
                .mapToDouble(order -> order.getTotalAmount() != null ? order.getTotalAmount() : 0.0)
                .sum();

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalUsers", totalUsers);
        summary.put("totalProducts", totalProducts);
        summary.put("totalOrders", totalOrders);
        summary.put("totalRevenue", totalRevenue);

        return ResponseEntity.ok(summary);
    }
}
