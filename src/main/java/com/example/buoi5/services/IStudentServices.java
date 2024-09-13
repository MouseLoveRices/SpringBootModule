package com.example.buoi5.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;

import com.example.buoi5.dtos.StudentDTO;
import com.example.buoi5.dtos.StudentImageDTO;
import com.example.buoi5.models.Student;
import com.example.buoi5.models.StudentImage;
import com.example.buoi5.responses.StudentResponse;

public interface IStudentServices {
    Student getStudentById(Long id);
    Page<StudentResponse> getStudents(Pageable pageable);
    List<Student> getStudent();
    Student saveStudent(StudentDTO StudentDTO);
    Student updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
    List<Student> findByName(String name);
    List<Student> findByThanhPho(String name);
    List<Student> findByThanhPhoAndTen(String name);
    List<Student> findByNgaySinhBetween(int startYear, int endYear);
    // List<Student> searchStudents(XepLoai xepLoai, String ten, int startYear, int endYear);
    StudentImage saveStudentImage(Long studentId, StudentImageDTO studentImageDTO);
    List<StudentImage> getAllStudentImages(Long studentId);
} 