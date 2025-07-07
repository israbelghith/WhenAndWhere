package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.SubCategory;
import com.waw.whenandwhere.repositories.SubCategoryRepository;

@Service
public class SubCategoryService {
	@Autowired
    private SubCategoryRepository subCategoryRepository;

    
    public SubCategory addSubCategory(SubCategory subCategory) {
        
        if (subCategory.getName() == null || subCategory.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("SubCategory name cannot be empty");
        }
        if (subCategory.getCategory() == null) {
            throw new IllegalArgumentException("SubCategory must be associated with a category");
        }
        return subCategoryRepository.save(subCategory);
    }

    
    public SubCategory updateSubCategory(Long id, SubCategory updatedSubCategory) {
        Optional<SubCategory> existingSubCategory = subCategoryRepository.findById(id);
        if (!existingSubCategory.isPresent()) {
            throw new IllegalArgumentException("SubCategory with id " + id + " not found");
        }

        SubCategory subCategory = existingSubCategory.get();
        
        if (updatedSubCategory.getName() != null && !updatedSubCategory.getName().trim().isEmpty()) {
            subCategory.setName(updatedSubCategory.getName());
        }
       
        if (updatedSubCategory.getCategory() != null) {
            subCategory.setCategory(updatedSubCategory.getCategory());
        }

        return subCategoryRepository.save(subCategory);
    }

   
    public SubCategory viewSubCategory(Long id) {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (!subCategory.isPresent()) {
            throw new IllegalArgumentException("SubCategory with id " + id + " not found");
        }
        return subCategory.get();
    }

    
    public List<SubCategory> viewAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    
    public void deleteSubCategory(Long id) {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (!subCategory.isPresent()) {
            throw new IllegalArgumentException("SubCategory with id " + id + " not found");
        }
        subCategoryRepository.deleteById(id);
    }
}
