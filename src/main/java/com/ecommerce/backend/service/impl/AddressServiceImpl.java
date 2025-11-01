package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.Address;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.AddressRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public Address addAddress(String userEmail, Address address) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getUserAddresses(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return addressRepository.findByUser(user);
    }

    @Override
    public Address getAddressById(Long id, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("You don’t have permission to access this address");
        }

        return address;
    }

    @Override
    public Address updateAddress(Long id, Address newAddress, String userEmail) {
        Address address = getAddressById(id, userEmail);

        address.setFullName(newAddress.getFullName());
        address.setPhone(newAddress.getPhone());
        address.setStreet(newAddress.getStreet());
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());
        address.setZipCode(newAddress.getZipCode());
        address.setCountry(newAddress.getCountry());

        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id, String userEmail) {
        Address address = getAddressById(id, userEmail);
        addressRepository.delete(address);
    }
}
