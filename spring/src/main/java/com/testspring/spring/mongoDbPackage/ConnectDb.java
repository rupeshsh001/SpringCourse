package com.testspring.spring.mongoDbPackage;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.testspring.spring.entities.Course;

public interface ConnectDb extends MongoRepository<Course, String> {


}
