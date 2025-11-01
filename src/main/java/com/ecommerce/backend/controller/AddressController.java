package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Address;
import com.ecommerce.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    // ➕ Add new address
    @PostMapping
    public ResponseEntity<Address> addAddress(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Address address
    ) {
        Address saved = addressService.addAddress(userDetails.getUsername(), address);
        return ResponseEntity.ok(saved);
    }

    // 📦 Get all addresses of the logged-in user
    @GetMapping
    public ResponseEntity<List<Address>> getUserAddresses(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<Address> addresses = addressService.getUserAddresses(userDetails.getUsername());
        return ResponseEntity.ok(addresses);
    }

    // 🔍 Get one specific address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Address address = addressService.getAddressById(id, userDetails.getUsername());
        return ResponseEntity.ok(address);
    }

    // ✏️ Update an address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long id,
            @RequestBody Address newAddress,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Address updated = addressService.updateAddress(id, newAddress, userDetails.getUsername());
        return ResponseEntity.ok(updated);
    }

    // ❌ Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        addressService.deleteAddress(id, userDetails.getUsername());
        return ResponseEntity.ok("✅ Address deleted successfully");
    }
}
