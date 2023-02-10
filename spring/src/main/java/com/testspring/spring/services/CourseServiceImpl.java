package com.testspring.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.testspring.spring.entities.Course;
import com.testspring.spring.errorHandLing.CourseAlreadyExistsException;
import com.testspring.spring.errorHandLing.CourseNotFoundException;
import com.testspring.spring.mongoDbPackage.ConnectDb;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private ConnectDb course;

    List<Course> list;

    @Override
    public List<Course> getCourses() {
        return course.findAll();
    }

    @Override
    public Optional<Course> getCourseById(String courseId) {
        Optional<Course> result = course.findById(courseId);
        if (!result.isPresent()) {
            throw new CourseNotFoundException("course not found!!!");
        }
        return result;
    }

    @Override
    public Course addCourse(Course newCourse) {
        Query query = new Query();
        query.addCriteria(where("title").is(newCourse.getTitle()));
        List<Course> allCourses = course.findAll();
        for (Course cc : allCourses) {
            if (newCourse.getTitle().equals(cc.getTitle())) {
                throw new CourseAlreadyExistsException("course already exist");
            }
        }
        return course.save(newCourse);
    }

    @Override
    public Optional<Course> updateCourse(String courseId, Course updatedCourse) {
        Optional<Course> result = course.findById(courseId);
        if (!result.isPresent()) {
            throw new CourseNotFoundException("course not found to Upate!!!");
        }
        Course updatedResult = result.get();
        updatedResult.setTitle(updatedCourse.getTitle() != null ? updatedCourse.getTitle() : updatedResult.getTitle());
        updatedResult.setAmount(updatedCourse.getAmount() > 100 ? updatedCourse.getAmount() : updatedResult.getAmount());
        updatedResult.setDurationInMonth(updatedCourse.getDurationInMonth() !=null ? updatedCourse.getDurationInMonth() : updatedResult.getDurationInMonth());
        updatedResult.setLanguageRequired(updatedCourse.getLanguageRequired() !=null ? updatedCourse.getLanguageRequired() : updatedResult.getLanguageRequired());
        updatedResult
                .setDescription(updatedCourse.getDescription() != null ? updatedCourse.getDescription()
                        : updatedResult.getDescription());
        course.save(updatedResult);
        return result;
    }

    @Override
    public Optional<Course> deleteCourse(String courseId) {
        Optional<Course> result = course.findById(courseId);
        if (!result.isPresent()) {
            throw new CourseNotFoundException("course not found to perform DELETE operation");
        }
        Course deletedResult = result.get();
        if(deletedResult!=null){
            course.delete(deletedResult);
        }
        return result;
    }

}
