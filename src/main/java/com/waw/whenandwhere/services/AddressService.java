package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.Address;
import com.waw.whenandwhere.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
    private AddressRepository addressRepository;

    public Address addAddress(Address address) {
        if (address.getCity() == null || address.getCity().trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        if (address.getPostalCode() == null || address.getPostalCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be empty");
        }
        if (address.getCountry() == null || address.getCountry().trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be empty");
        }
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (!existingAddress.isPresent()) {
            throw new IllegalArgumentException("Address with id " + id + " not found");
        }

        Address address = existingAddress.get();

        if (updatedAddress.getCity() != null && !updatedAddress.getCity().trim().isEmpty()) {
            address.setCity(updatedAddress.getCity());
        }
        if (updatedAddress.getPostalCode() != null && !updatedAddress.getPostalCode().trim().isEmpty()) {
            address.setPostalCode(updatedAddress.getPostalCode());
        }
        if (updatedAddress.getCountry() != null && !updatedAddress.getCountry().trim().isEmpty()) {
            address.setCountry(updatedAddress.getCountry());
        }
        if (updatedAddress.getRegion() != null) {
            address.setRegion(updatedAddress.getRegion());
        }

        return addressRepository.save(address);
    }

    public Address viewAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw new IllegalArgumentException("Address with id " + id + " not found");
        }
        return address.get();
    }

    public List<Address> viewAllAddresses() {
        return addressRepository.findAll();
    }

    public void deleteAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw new IllegalArgumentException("Address with id " + id + " not found");
        }

        Address existingAddress = address.get();

        
        if (existingAddress.getRegion() != null) {
            throw new IllegalStateException("Cannot delete address associated with a region");
        }

        addressRepository.deleteById(id);
    }
}
