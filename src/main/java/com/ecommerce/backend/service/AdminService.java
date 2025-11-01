package com.ecommerce.backend.service;

import com.ecommerce.backend.model.User;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AdminService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    // 👥 Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 📦 Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 🧾 Get single order by ID
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // 🗑️ Delete a user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // 🚚 Update order status
    public Order updateOrderStatus(Long orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + orderId);
        }
    }
}
