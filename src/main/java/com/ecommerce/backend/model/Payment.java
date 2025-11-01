package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod;   // Example: CREDIT_CARD, UPI, COD
    private String paymentStatus;   // Example: SUCCESS, FAILED
    private Double amount;          // Total amount paid
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;            // Which order this payment belongs to
}
