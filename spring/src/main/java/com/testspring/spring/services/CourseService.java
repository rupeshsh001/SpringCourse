package com.testspring.spring.services;

import java.util.List;
import java.util.Optional;

import com.testspring.spring.entities.Course;

public interface CourseService {
    public List<Course> getCourses();
    public Optional<Course> getCourseById(String courseId);
    public Optional<Course> updateCourse(String courseId,Course updatedCourse);
    public Course addCourse(Course course);
    public Optional<Course> deleteCourse(String courseId);

}
