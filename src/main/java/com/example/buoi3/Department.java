package com.example.buoi3;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department")
public class Department {

    private String nameDepartment;
    private String[] users;

    // Constructor mặc định
    public Department() {
    }

    public Department(String nameDepartment, String[] users) {
        this.nameDepartment = nameDepartment;
        this.users = users;
    }

    // Getters và Setters
    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}
