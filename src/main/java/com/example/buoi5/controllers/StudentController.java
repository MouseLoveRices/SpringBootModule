package com.example.buoi5.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.buoi5.dtos.StudentDTO;
import com.example.buoi5.exceptions.ResourceNotFoundException;
import com.example.buoi5.models.Student;
import com.example.buoi5.responses.ApiResponse;
import com.example.buoi5.responses.StudentListResponse;
import com.example.buoi5.responses.StudentResponse;
import com.example.buoi5.services.StudentServices;
import org.springframework.validation.FieldError;
import jakarta.validation.Valid;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/admin/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    private final StudentServices studentServices;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getStudents1(
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "5") int size){
       
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdAt").descending());
        Page<StudentResponse> studentResponse = studentServices.getStudents(pageable);
        
        int totalPages = studentResponse.getTotalPages();
        List<StudentResponse> responeList = studentResponse.getContent();
        
        StudentListResponse studentListResponse = StudentListResponse.builder()
                                            .studentResponseList(responeList)
                                            .totalPages(totalPages)
                                            .build();
        
        ApiResponse apiResponse = ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Show student successfully")
                        .data(studentListResponse)                       
                        .build();
    return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> index(){
        ApiResponse apiResponse = ApiResponse.builder()
                        .data(studentServices.getStudent())
                        .status(HttpStatus.OK.value())
                        .message("Ok")
                        .build();
    return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody StudentDTO studentDTO, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                                        .map(FieldError::getDefaultMessage).toList();
            
            ApiResponse apiResponse = ApiResponse.builder()
                                .data(errors)
                                .message("Validation failed")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Student student = studentServices.saveStudent(studentDTO);
        ApiResponse apiResponse = ApiResponse.builder()
                                        .data(student)
                                        .message("Insert successfully")
                                        .status(HttpStatus.OK.value())
                                        .build();
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                                        .map(FieldError::getDefaultMessage).toList();
            
            ApiResponse apiResponse = ApiResponse.builder()
                            .data(errors)
                            .message("validation Failed")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Student student = studentServices.updateStudent(id, studentDTO);
        if(student == null){
            throw new ResourceNotFoundException("Khong tim thay voi id: "+id);

        }
        ApiResponse apiResponse = ApiResponse.builder()
                                .data(student)
                                .message("Update succesfull")
                                .status(HttpStatus.OK.value())
                                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Student student = studentServices.getStudentById(id);
        if(student == null){
            throw new ResourceNotFoundException("Khong tim thay voi id: "+id);
        }
        studentServices.deleteStudent(id);
        ApiResponse apiResponse = ApiResponse.builder()
                    .data(null)
                    .message("delete successfully")
                    .status(HttpStatus.OK.value())
                    .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search1")
    public ResponseEntity<ApiResponse> search1(@RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder()
                    .data(studentServices.findByName(name))
                    .message("Search successfully")
                    .status(HttpStatus.OK.value())
                    .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search2")
    public ResponseEntity<ApiResponse> search2(@RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder()
                    .data(studentServices.findByThanhPho(name))
                    .message("Search successfully")
                    .status(HttpStatus.OK.value())
                    .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search3")
    public ResponseEntity<ApiResponse> search3(@RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder()
                    .data(studentServices.findByThanhPhoAndTen(name))
                    .message("Search successfully")
                    .status(HttpStatus.OK.value())
                    .build();
        return ResponseEntity.ok(apiResponse);
    }
}
