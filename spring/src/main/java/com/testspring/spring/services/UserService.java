package com.testspring.spring.services;
import java.util.List;


import com.testspring.spring.entities.User;

public interface UserService {
    public List<User> getAllUser();
    public User addUser(User user);
    public User getUserByID(String userId);
    public User delteUserByID(String userId);

}
