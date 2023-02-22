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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testspring.spring.entities.Course;
import com.testspring.spring.errorHandLing.AlreadyExistsException;
import com.testspring.spring.errorHandLing.NotFoundException;
import com.testspring.spring.errorHandLing.ErrorResponse;
import com.testspring.spring.services.CourseService;

import jakarta.validation.Valid;

@RestController //Rest===?representational state transfer
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @GetMapping("")
    public List<Course> getCourses(){
        return this.courseService.getCourses();
    }

    @GetMapping("search")
    public List<Course> getCourseByTitle(@RequestParam("searchby") String title){
        return this.courseService.getCourseByTitle(title);
    }

    @GetMapping("/{courseId}")
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
    public void deleteCourse(@PathVariable String courseId){
        this.courseService.deleteCourse(courseId);
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
