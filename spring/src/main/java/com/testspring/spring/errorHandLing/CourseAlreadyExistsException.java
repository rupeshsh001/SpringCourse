package com.testspring.spring.errorHandLing;

public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }
    public CourseAlreadyExistsException(String message){
        super(message);
    }
    public CourseAlreadyExistsException(Throwable cause){
        super(cause);
    }
}
