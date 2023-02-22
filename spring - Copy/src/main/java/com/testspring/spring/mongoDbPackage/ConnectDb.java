package com.testspring.spring.mongoDbPackage;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.testspring.spring.entities.Course;
public interface ConnectDb extends MongoRepository<Course, String> {
    @Query(value="{'title':?0}")
    // @Query(value="{'title':{'$regex': ?0,'$options':'i'}}")
    List<Course> findByTitle(String title);

    @Aggregation(pipeline = {
        "{ $match: { $or:[{ id: '63e49fc82055a914b5c29543' }, { id: '63e5f9321afb065e2c4149fa' }] }}"
        })
    List<Course> findCourse();
    

}
