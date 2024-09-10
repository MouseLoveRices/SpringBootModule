package com.example.buoi5.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



import com.example.buoi5.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    Page<Student> findAll(Pageable pageable);
    List<Student> findByTenContainingIgnoreCase(String ten);

    @Query("SELECT s FROM Student s WHERE s.thanhPho like LOWER(CONCAT('%',:name,'%'))")
    List<Student> findByThanhPho(String name);
    
    @Query("SELECT s FROM Student s WHERE s.thanhPho like LOWER(CONCAT('%',:name,'%')) OR s.ten like LOWER(CONCAT('%',:name,'%'))")
    List<Student> findByThanhPhoAndTen(String name);
} 
