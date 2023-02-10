package com.testspring.spring.errorHandLing;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public CourseNotFoundException(String message){
        super(message);
    }
    public CourseNotFoundException(Throwable cause){
        super(cause);
    }
}
