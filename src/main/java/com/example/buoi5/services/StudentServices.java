package com.example.buoi5.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.buoi5.dtos.StudentDTO;
import com.example.buoi5.models.Student;
import com.example.buoi5.models.XepLoai;
import com.example.buoi5.repositories.StudentRepository;
import com.example.buoi5.responses.StudentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServices implements IStudentServices{
    private final StudentRepository studentRepository;
    
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student không tìm thấy"));
    }

    @Override
    public Page<StudentResponse> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
            .map(StudentResponse::fromStudent);
    }

    @Override
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(StudentDTO studentDTO) {
       Student student = Student.builder()
                                .ten(studentDTO.getTen())
                            .thanhPho(studentDTO.getThanhPho())
                            .ngaySinh(studentDTO.getNgaySinh())
                            .xepLoai(XepLoai.fromTen(studentDTO.getXepLoai()))
                            .build();
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, StudentDTO studentDTO) {
        Student studentExisting = getStudentById(id);
        studentExisting.setTen(studentDTO.getTen());
        studentExisting.setThanhPho(studentDTO.getThanhPho());
        studentExisting.setXepLoai(XepLoai.fromTen(studentDTO.getXepLoai()));
        studentExisting.setNgaySinh(studentDTO.getNgaySinh());
        return studentRepository.save(studentExisting);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByTenContainingIgnoreCase(name);
    }

    @Override
    public List<Student> findByThanhPho(String name) {
        return studentRepository.findByThanhPho(name);
    }

    @Override
    public List<Student> findByThanhPhoAndTen(String name) {
        return studentRepository.findByThanhPhoAndTen(name);
    }
}
