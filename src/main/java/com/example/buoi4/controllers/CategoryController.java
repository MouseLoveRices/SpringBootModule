package com.example.buoi4.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.buoi4.dtos.CategoryDTO;
import com.example.buoi4.model.Category;
import com.example.buoi4.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public List<Category> index(){
        return categoryService.getAllCategories();
    }
    
    @PostMapping("")
    public Category postcategory(@RequestBody CategoryDTO categoryDTO) {
        
        return categoryService.saveCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public Category putCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(id, categoryDTO);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id, @RequestBody Category category){
        categoryService.deleteCategory(id);
        return "successfully delete with id: "+id;
    }

    @PostMapping("/insert1")
    public String postCategory1(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError fieldError: result.getFieldErrors()){
                errors.add(fieldError.getDefaultMessage());
            }
            return errors.toString();
        }
        categoryService.saveCategory(categoryDTO);
        return "insert"+categoryDTO;
    }
    
    
}
