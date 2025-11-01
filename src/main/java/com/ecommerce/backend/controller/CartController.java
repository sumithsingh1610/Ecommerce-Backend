package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long productId,
                                          @RequestParam(defaultValue = "1") int quantity,
                                          Principal principal) {
        String email = principal.getName();
        Cart cart = cartService.addToCart(productId, quantity, email);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(Principal principal) {
        String email = principal.getName();
        Cart cart = cartService.getCartByUser(email);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long productId, Principal principal) {
        String email = principal.getName();
        Cart cart = cartService.removeFromCart(productId, email);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(Principal principal) {
        String email = principal.getName();
        cartService.clearCart(email);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
