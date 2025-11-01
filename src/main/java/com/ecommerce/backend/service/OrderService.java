package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(String userEmail);
    List<Order> getUserOrders(String userEmail);
    Order getOrderById(Long orderId, String userEmail);
}
