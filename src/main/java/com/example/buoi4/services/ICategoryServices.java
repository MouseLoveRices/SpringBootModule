package com.example.buoi4.services;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.buoi4.dtos.CategoryDTO;
import com.example.buoi4.model.Category;
import com.example.buoi4.responses.CategoryResponse;

public interface ICategoryServices {
    Category getCategory(Long id);
    List<Category> getAllCategories();
    Category saveCategory( CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category updateCategory(Long id, CategoryDTO categoryDTO);
    Page<CategoryResponse> getAllCategories(PageRequest pageRequest);
} 
