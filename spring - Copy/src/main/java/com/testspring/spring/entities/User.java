package com.testspring.spring.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection = "user")
public class User {
    @Id
    private String id;

    @NotBlank(message = "fullName is mandatory")
    @NotNull(message = "fullName is mandatory")
    private String fullName;

    @NotBlank(message = "email is mandatory")
    @NotNull(message = "email is mandatory")
    @Pattern(regexp = "^[a-z0-9.-_]+@[a-z]+[.][a-z]{2,3}$", message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "phone is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "phone number should be of size 10")
    private String phone;
    
    private List<String> courses;

    private String userType="user";

    public User(String fullName, String email, String phone, List<String> courses) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.courses = courses;
    }

    public String getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCourses() {
        return this.courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
