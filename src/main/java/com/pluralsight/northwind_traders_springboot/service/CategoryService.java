package com.pluralsight.northwind_traders_springboot.service;

import com.pluralsight.northwind_traders_springboot.model.Category;
import com.pluralsight.northwind_traders_springboot.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }


}
