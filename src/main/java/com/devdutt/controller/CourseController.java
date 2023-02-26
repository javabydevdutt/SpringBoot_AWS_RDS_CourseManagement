package com.devdutt.controller;

import com.devdutt.entity.Course;
import com.devdutt.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping("/save")
    public ResponseEntity<String> addCourses(@RequestBody Course course) {
        ResponseEntity<String> response = null;
        //call service class method
        Integer id = service.insert(course);
        if (id < 0) {
            response = new ResponseEntity<>("Unable to save", HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>("Couse Register Successfully with id :- [" + id + "]", HttpStatus.CREATED);
        }
        return response;
    }//addCourses()

    @GetMapping("/{cid}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer cid) {
        //call service class method
        Course result = service.getById(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> allCourses = service.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PutMapping("/update/{cid}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Integer cid) {
        ResponseEntity<String> response = null;
        //call service class method
        Course crs = service.getById(cid);
        if (cid == crs.getCid()) {
            crs.setName(course.getName());
            crs.setPrice(course.getPrice());
            Integer id = service.insert(crs);
            response = new ResponseEntity<>("Update Course Successfully with id:- [" + id + "]", HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("Unable to update", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer cid) {
        String status = service.deleteById(cid);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}//class
