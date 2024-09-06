package com.example.buoi4.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.buoi4.dtos.CategoryDTO;
import com.example.buoi4.model.Category;
import com.example.buoi4.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryServices{
    private final CategoryRepository categoryRepository;

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return null;
    }

    @Override
    public Category saveCategory(CategoryDTO categoryDTO) {
        // return categoryRepository.save(category);
        Category category = Category
            .builder()
            .name(categoryDTO.getCategoryName())
            .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO){
        Category cate = getCategory(id);
        cate.setName(categoryDTO.getCategoryName());
        return categoryRepository.save(cate);
    }
   
}
