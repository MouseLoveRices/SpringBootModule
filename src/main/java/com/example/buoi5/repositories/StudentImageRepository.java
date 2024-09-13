package com.example.buoi5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.buoi5.models.StudentImage;

public interface StudentImageRepository extends JpaRepository<StudentImage, Long>{
    List<StudentImage> findByStudentId(Long id);
    
} 
