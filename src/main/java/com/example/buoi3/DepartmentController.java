package com.example.buoi3;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepo;

    @Autowired
    UserRepository userRepo;

    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        try {
            for(String userID : department.getUsers()){
                Optional<User> userData = userRepo.findById(userID);
                if(!userData.isPresent()){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            Department _department = departmentRepo.save(department);
            return new ResponseEntity<>(_department, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartment() {
        try {
            List<Department> departments = departmentRepo.findAll();
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);  // Trả về danh sách department
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
