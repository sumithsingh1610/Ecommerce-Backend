package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Each order belongs to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ✅ The items in the order (we’ll use CartItem for now)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<CartItem> items;

    // ✅ Total price of all items
    private double totalAmount;

    // ✅ Payment status (PENDING / SUCCESS)
    private String paymentStatus;

    // ✅ Timestamp of order placement
    private LocalDateTime orderDate;
}
