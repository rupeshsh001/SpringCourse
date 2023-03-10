package com.testspring.spring.mongoDbPackage;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

import com.testspring.spring.entities.User;

public interface ConnectUser extends MongoRepository<User, String> {

    @Query(value ="{'id':?0}")
    List<User> findByID(String id);

    @Aggregation(pipeline = {
        "{ $match: { $or:[{ fullName: { $regex: ?0,  $options: 'i' } }, { email: { $regex: ?0,  $options: 'i' } }] }}"
        })
    List<User> searchUser(String search);

}
