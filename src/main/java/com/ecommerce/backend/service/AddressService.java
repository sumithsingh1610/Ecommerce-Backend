package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Address;

import java.util.List;

public interface AddressService {

    // ➕ Add new address
    Address addAddress(String userEmail, Address address);

    // 📋 Get all addresses for a user
    List<Address> getUserAddresses(String userEmail);

    // 🧾 Get single address
    Address getAddressById(Long id, String userEmail);

    // ✏️ Update address
    Address updateAddress(Long id, Address newAddress, String userEmail);

    // ❌ Delete address
    void deleteAddress(Long id, String userEmail);
}
