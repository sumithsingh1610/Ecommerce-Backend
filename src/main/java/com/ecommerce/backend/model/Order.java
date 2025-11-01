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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount;
    private String paymentStatus;
    private LocalDateTime orderDate;

    // 🔗 Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 🔗 Relationship with Cart Items
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items;

    // 🏡 New — Relationship with Address
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
