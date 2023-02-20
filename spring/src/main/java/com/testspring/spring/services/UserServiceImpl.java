package com.testspring.spring.services;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testspring.spring.entities.User;
import com.testspring.spring.errorHandLing.AlreadyExistsException;
import com.testspring.spring.errorHandLing.NotFoundException;
import com.testspring.spring.mongoDbPackage.ConnectUser;
import static org.springframework.data.mongodb.core.query.Criteria.where;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private ConnectUser user;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> getAllUser() {
        return user.findAll();
    }

    @Override
    public User getUserByID(String userId) {
        List<User> result = user.findByID(userId);
        if(result.isEmpty()) {
            throw new NotFoundException(userId+" :User Not exist");
        }
        return result.get(0);
    }

    @Override
    public User addUser(User newUser) {
        Query query = new Query();
        query.addCriteria(where("email").is(newUser.getEmail()));
        List<User> result=mongoTemplate.find(query,User.class);
        if(!result.isEmpty()){
            throw new AlreadyExistsException("User already exist");
        }
        return user.save(newUser);
    }

    @Override
    public User delteUserByID(String userId) {
        Query query = new Query();
        query.addCriteria(where("id").is(userId));
        List<User> result=mongoTemplate.find(query,User.class);
        if(result.isEmpty()) {
            throw new NotFoundException(userId+" :User Not exist");
        }
        user.delete(result.get(0));
        return result.get(0);
    }

   

}
