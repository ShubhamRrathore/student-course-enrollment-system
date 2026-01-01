package Hackerrank.codingapi.controllers;

import Hackerrank.codingapi.payloads.coursedtos.CourseCountRespons;
import Hackerrank.codingapi.payloads.coursedtos.CourseDTO;
import Hackerrank.codingapi.Service.Impl.CourseServiceImpl;
import Hackerrank.codingapi.entities.Course;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
@SecurityRequirement(name = "bearerAuth")

public class CourseController {

   private final CourseServiceImpl courseService;


    // ---------------- CREATE SINGLE COURSE ----------------
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.createCourse(course));
    }

    // ---------------- CREATE COURSES IN BULK ----------------
    @PostMapping("/bulk")
    public ResponseEntity<List<CourseDTO>> createCourses(@RequestBody List<CourseDTO> courses) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.createCourses(courses));
    }

    // ---------------- GET ALL COURSES ----------------
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourse() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // ---------------- GET COURSES WITH MIN STUDENTS ----------------
    @GetMapping("/min-students")
    public ResponseEntity<List<CourseCountRespons>> getCoursesWithMinStudents(
            @RequestParam("minStudents") Long minStudents) {

        return ResponseEntity.ok(
                courseService.getCoursesWithMinStudents(minStudents)
        );
    }

    // ---------------- GET COURSES WITH NO STUDENTS ----------------
    @GetMapping("/no-students")
    public ResponseEntity<List<CourseCountRespons>> getCoursesWithNoStudents() {
        return ResponseEntity.ok(courseService.getCoursesWithNoStudents());
    }


}
