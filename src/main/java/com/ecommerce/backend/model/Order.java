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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<CartItem> items;

    private double totalAmount;

    private String paymentStatus;

    private LocalDateTime orderDate;

    // 🏡 New field: Link Address
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
