package com.devdutt.service;

import com.devdutt.entity.Course;

import java.util.List;

public interface CourseService {

    public Integer insert(Course course);

    public Course getById(Integer cid);

    public List<Course> getAllCourses();

    public String deleteById(Integer cid);
}
