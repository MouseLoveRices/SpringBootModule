package com.example.buoi5.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.example.buoi5.dtos.StudentDTO;
import com.example.buoi5.dtos.StudentImageDTO;
import com.example.buoi5.exceptions.ResourceNotFoundException;
import com.example.buoi5.models.Student;
import com.example.buoi5.models.StudentImage;
import com.example.buoi5.responses.ApiResponse;
import com.example.buoi5.responses.StudentListResponse;
import com.example.buoi5.responses.StudentResponse;
import com.example.buoi5.services.StudentServices;


import org.springframework.validation.FieldError;
import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/images/{id}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long id){
        StudentImage student = studentServices.getImageById(id);
        if(student == null){
            throw new ResourceNotFoundException("Khong tim thay hinh anh voi id: "+id);
        }
        studentServices.deleteImage(id);
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

    @GetMapping("/search4")
    public ResponseEntity<ApiResponse> search3(@RequestParam int startYear, int endYear){
        ApiResponse apiResponse = ApiResponse.builder()
                        .data(studentServices.findByNgaySinhBetween(startYear, endYear))
                        .message("Search successfully")
                        .status(HttpStatus.OK.value())
                        .build();
        return ResponseEntity.ok(apiResponse);       
    }

    @GetMapping("/getAllImage/{id}")
    public ResponseEntity<ApiResponse> getAllImage(@PathVariable long id){
        ApiResponse apiResponse = ApiResponse.builder()
                            .data(studentServices.getAllStudentImages(id))
                            .status(HttpStatus.OK.value())
                            .message("Get successfully")
                            .build();
        return ResponseEntity.ok(apiResponse);  
    }

    // @PostMapping("/uploads/{id}")
    // public ResponseEntity<ApiResponse> uploads(@PathVariable Long id, @Valid @RequestBody StudentImageDTO studentImageDTO, BindingResult result){
    //         if( result.hasErrors()){
    //             List<String> errors = result.getFieldErrors().stream()
    //                                     .map(FieldError::getDefaultMessage).toList();
    //             ApiResponse apiResponse = ApiResponse.builder()
    //                         .data(errors)
    //                         .message("Validation failed")
    //                         .status(HttpStatus.BAD_REQUEST.value())
    //                         .build();
    //             return ResponseEntity.badRequest().body(apiResponse);
    //         }
    //     ApiResponse apiResponse = ApiResponse.builder()
    //                     .status(HttpStatus.OK.value())
    //                     .message("Upload successfully")
    //                     .data(studentServices.saveStudentImage(id, studentImageDTO))
    //                     .build();
    //     return ResponseEntity.ok(apiResponse);                    
    // }

    @PostMapping(value = "/uploads/{id}")
public ResponseEntity<ApiResponse> uploads(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
    if (file.isEmpty()) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Please select a file to upload")
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    String fileName = storeFile(file);
    StudentImageDTO studentImageDTO = StudentImageDTO.builder()
            .imageUrl(fileName)
            .build();
    ApiResponse apiResponse = ApiResponse.builder()
            .status(HttpStatus.OK.value())
            .message("Upload successfully")
            .data(studentServices.saveStudentImage(id, studentImageDTO))
            .build();
    return ResponseEntity.ok(apiResponse);                                
}

    private String storeFile(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString()+"_"+fileName;
        java.nio.file.Path uploadDdir = Paths.get("upload");
        if(!Files.exists(uploadDdir)){
            Files.createDirectories(uploadDdir);
        }
        java.nio.file.Path destination = Paths.get(uploadDdir.toString(),uniqueFileName);
        Files.copy(file.getInputStream(),destination,StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<?> viewImage(@PathVariable String imageName){
        try {
            java.nio.file.Path imagePath = Paths.get("upload/"+imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());
            
            if(resource.exists()){
                return ResponseEntity.ok()
                                .contentType(MediaType.IMAGE_JPEG)
                                .body(resource);
            }else{
                return ResponseEntity.ok()
                                .contentType(MediaType.IMAGE_JPEG)
                                .body(new UrlResource(Paths.get("upload/notfound.jpeg").toUri()));
            } 
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
