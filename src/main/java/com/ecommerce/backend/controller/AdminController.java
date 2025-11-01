package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.User;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 👥 Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // 📦 Get all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }

    // 🧾 Get order by ID
    @GetMapping("/orders/{orderId}")
    public Optional<Order> getOrderById(@PathVariable Long orderId) {
        return adminService.getOrderById(orderId);
    }

    // 🗑️ Delete user
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return "User deleted successfully!";
    }

    // 🚚 Update order status
    @PutMapping("/orders/{orderId}/status")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return adminService.updateOrderStatus(orderId, status);
    }
}
