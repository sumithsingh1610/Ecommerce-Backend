package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
}
