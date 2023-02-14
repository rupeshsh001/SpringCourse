package com.testspring.spring.mongoDbPackage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

import com.testspring.spring.entities.User;

public interface ConnectUser extends MongoRepository<User, String> {

    @Query(value ="{'id':?0}")
    List<User> findByID(String id);

}
