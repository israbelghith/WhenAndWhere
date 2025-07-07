package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.Category;
import com.waw.whenandwhere.repositories.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	

   
    public Category addCategory(Category category) {
        
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        return categoryRepository.save(category);
    }

  
    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (!existingCategory.isPresent()) {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }

        Category category = existingCategory.get();
       
        if (updatedCategory.getName() != null && !updatedCategory.getName().trim().isEmpty()) {
            category.setName(updatedCategory.getName());
        }
        if (updatedCategory.getDescription() != null) {
            category.setDescription(updatedCategory.getDescription());
        }
        

        return categoryRepository.save(category);
    }

   
    public Category viewCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
        return category.get();
    }

    
    public List<Category> viewAllCategories() {
        return categoryRepository.findAll();
    }

 
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
        
        
        Category existingCategory = category.get();
        if (!existingCategory.getSubcategories().isEmpty() || 
            !existingCategory.getActivities().isEmpty()) {
            throw new IllegalStateException("Cannot delete category with associated subcategories or activities");
        }
        
        categoryRepository.deleteById(id);
    }
}
