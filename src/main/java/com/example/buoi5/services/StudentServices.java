package com.example.buoi5.services;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.buoi5.dtos.StudentDTO;
import com.example.buoi5.dtos.StudentImageDTO;
import com.example.buoi5.models.Student;
import com.example.buoi5.models.StudentImage;
import com.example.buoi5.models.XepLoai;
import com.example.buoi5.repositories.StudentImageRepository;
import com.example.buoi5.repositories.StudentRepository;
import com.example.buoi5.responses.StudentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServices implements IStudentServices{
    private final StudentRepository studentRepository;
    private final StudentImageRepository studentImageRepository;

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

    @Override
    public List<Student> findByNgaySinhBetween(int startYear,int endYear){
        return studentRepository.findByNgaySinhBetween(startYear, endYear);
    }

    // @Override
    // public List<Student> searchStudents(XepLoai xepLoai, String ten, int startYear, int endYear){
    //     return studentRepository.search(xepLoai,ten,startYear,endYear);
    // }

    @Override 
    public StudentImage saveStudentImage(Long studentId, StudentImageDTO studentImageDTO){
        Student student = getStudentById(studentId);
        StudentImage studentImage = StudentImage.builder()
                        .student(student)
                        .iamgeUrl(studentImageDTO.getImageUrl())
                        .build();
        
        int size = studentImageRepository.findByStudentId(studentId).size();
        if(size>=4){
            throw new InvalidParameterException("Moix sin hvien chi co the toi da 4 hinh");

        }
        return studentImageRepository.save(studentImage);
    }

    @Override
    public List<StudentImage> getAllStudentImages(Long studentId){
        return studentImageRepository.findByStudentId(studentId);
    }



    //////

}
