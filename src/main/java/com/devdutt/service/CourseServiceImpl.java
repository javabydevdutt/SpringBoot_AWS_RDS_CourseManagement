package com.devdutt.service;

import com.devdutt.entity.Course;
import com.devdutt.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public Integer insert(Course course) {
        Course result = repository.save(course);
        return result.getCid();
    }

    @Override
    public Course getById(Integer cid) {
        Optional<Course> optionalCourse = repository.findById(cid);
        Course crs = null;
        if (optionalCourse.isPresent()) {
            crs = optionalCourse.get();
        }
        return crs;
    }

    @Override
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @Override
    public String deleteById(Integer cid) {
        if (repository.existsById(cid)) {
            repository.deleteById(cid);
            return "Delete Success";
        } else {
            return "No Record Found";
        }
    }
}
