package com.testspring.spring.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.testspring.spring.entities.User;
import com.testspring.spring.errorHandLing.AlreadyExistsException;
import com.testspring.spring.errorHandLing.ErrorResponse;
import com.testspring.spring.errorHandLing.NotFoundException;
import com.testspring.spring.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping (value = "", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return this.userService.getAllUser();
    }

    @RequestMapping (value = "/{userId}", method = RequestMethod.GET)
    public User getUserByID(@PathVariable String userId) {
        return this.userService.getUserByID(userId);
    }

    @RequestMapping (value = "/addUser", method = RequestMethod.POST)
    public User addUser(@Valid @RequestBody User user) {
        System.out.println(user+ " user here");
        return this.userService.addUser(user);
    }

    @RequestMapping(value="/delete/{userId}")
    public User delteUserByID(@PathVariable String userId) {
        return this.userService.delteUserByID(userId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AlreadyExistsException err) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(err.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException err) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(err.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
