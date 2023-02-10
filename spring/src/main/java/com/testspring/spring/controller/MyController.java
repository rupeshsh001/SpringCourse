package com.testspring.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testspring.spring.entities.Course;
import com.testspring.spring.errorHandLing.CourseAlreadyExistsException;
import com.testspring.spring.errorHandLing.CourseNotFoundException;
import com.testspring.spring.errorHandLing.ErrorResponse;
import com.testspring.spring.services.CourseService;

import jakarta.validation.Valid;

@RestController //Rest===?representational state transfer
public class MyController {

    @Autowired
    private CourseService courseService;
    
    @RequestMapping (value = "/", method = RequestMethod.GET)
    public HashMap<String, Object> index() {
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("developerName", "Rupesh Sharma");
        obj.put("projectName", "Course");
        obj.put("version", 1.0);
        return obj;
    }

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return this.courseService.getCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Optional<Course> getCourseById(@PathVariable String courseId){
        return this.courseService.getCourseById(courseId);
    }

    @PostMapping("/addCourse")
    public Course addCourse(@Valid @RequestBody Course course){
        return this.courseService.addCourse(course);
    }

    @PutMapping("/updatecourse/{courseId}")
    public Optional<Course> updateCourse(@Valid @PathVariable String courseId,@RequestBody Course updatedCourse){
        return this.courseService.updateCourse(courseId,updatedCourse);
    }

    @DeleteMapping("/delete/{courseId}")
    public Optional<Course> deleteCourse(@PathVariable String courseId){
        return this.courseService.deleteCourse(courseId);
    }
    

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(CourseAlreadyExistsException err) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(err.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(CourseNotFoundException err) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(err.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
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
    
}
