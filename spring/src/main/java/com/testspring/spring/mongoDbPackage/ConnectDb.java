package com.testspring.spring.mongoDbPackage;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.testspring.spring.entities.Course;
@Repository
public interface ConnectDb extends MongoRepository<Course, String> {
    @Query(value="{'title':?0}")
    // @Query(value="{'title':{'$regex': ?0,'$options':'i'}}")
    List<Course> findByTitle(String title);

}
