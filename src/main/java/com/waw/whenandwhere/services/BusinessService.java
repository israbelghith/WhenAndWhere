package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.Business;
import com.waw.whenandwhere.repositories.BusinessRepository;

@Service
public class BusinessService {

	@Autowired
    private BusinessRepository businessRepository;

    public Business addBusiness(Business business) {
        if (business.getUserName() == null || business.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (business.getPassword() == null || business.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (business.getMail() == null || business.getMail().trim().isEmpty()) {
            throw new IllegalArgumentException("Mail cannot be empty");
        }
        return businessRepository.save(business);
    }

    public Business updateBusiness(Long id, Business updatedBusiness) {
        Optional<Business> existingBusiness = businessRepository.findById(id);
        if (!existingBusiness.isPresent()) {
            throw new IllegalArgumentException("Business with id " + id + " not found");
        }

        Business business = existingBusiness.get();

        if (updatedBusiness.getUserName() != null && !updatedBusiness.getUserName().trim().isEmpty()) {
            business.setUserName(updatedBusiness.getUserName());
        }
        if (updatedBusiness.getPassword() != null && !updatedBusiness.getPassword().trim().isEmpty()) {
            business.setPassword(updatedBusiness.getPassword());
        }
        if (updatedBusiness.getMail() != null && !updatedBusiness.getMail().trim().isEmpty()) {
            business.setMail(updatedBusiness.getMail());
        }
        if (updatedBusiness.getPhone() != null && !updatedBusiness.getPhone().trim().isEmpty()) {
            business.setPhone(updatedBusiness.getPhone());
        }
        if (updatedBusiness.getDescription() != null && !updatedBusiness.getDescription().trim().isEmpty()) {
            business.setDescription(updatedBusiness.getDescription());
        }

        return businessRepository.save(business);
    }

    public Business viewBusiness(Long id) {
        Optional<Business> business = businessRepository.findById(id);
        if (!business.isPresent()) {
            throw new IllegalArgumentException("Business with id " + id + " not found");
        }
        return business.get();
    }

    public List<Business> viewAllBusinesses() {
        return businessRepository.findAll();
    }

    public void deleteBusiness(Long id) {
        Optional<Business> business = businessRepository.findById(id);
        if (!business.isPresent()) {
            throw new IllegalArgumentException("Business with id " + id + " not found");
        }

        Business existingBusiness = business.get();

        
        if (!existingBusiness.getActivities().isEmpty() || !existingBusiness.getPromotions().isEmpty()) {
            throw new IllegalStateException("Cannot delete business with associated activities or promotions");
        }

        businessRepository.deleteById(id);
    }
}
