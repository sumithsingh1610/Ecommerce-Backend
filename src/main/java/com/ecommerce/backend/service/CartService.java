package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Cart;

public interface CartService {
    Cart addToCart(Long productId, int quantity, String userEmail);
    Cart removeFromCart(Long productId, String userEmail);
    Cart getCartByUser(String userEmail);
    void clearCart(String userEmail);
}
