package com.example.buoi4.services;

import java.util.*;

import com.example.buoi4.dtos.CategoryDTO;
import com.example.buoi4.model.Category;

public interface ICategoryServices {
    Category getCategory(Long id);
    List<Category> getAllCategories();
    Category saveCategory( CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category updateCategory(Long id, CategoryDTO categoryDTO);
} 
