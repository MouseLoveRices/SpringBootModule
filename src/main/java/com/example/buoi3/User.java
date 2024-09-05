package com.example.buoi3;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userBaiTap1")
public class User {
    private String name;
    private int age;
    private String position;
    private String[] skills;

    public User(String name, int age, String position, String[] skills) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    
    
}
