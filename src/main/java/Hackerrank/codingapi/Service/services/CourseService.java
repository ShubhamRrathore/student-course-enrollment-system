package Hackerrank.codingapi.Service.services;

import Hackerrank.codingapi.payloads.coursedtos.CourseCountRespons;
import Hackerrank.codingapi.payloads.coursedtos.CourseDTO;
import Hackerrank.codingapi.entities.Course;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CourseService {

    // -----------------------
    // Basic CRUD
    // -----------------------
    Course createCourse(Course course);
    List<CourseDTO> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    List<CourseDTO> createCourses( List<CourseDTO> courses);


    // -----------------------
    // Course Specific Queries
    // -----------------------
    List<CourseCountRespons> getCoursesWithMinStudents(Long minStudents);     // courses with more than X students
    List<CourseCountRespons> getCoursesWithNoStudents();                     // courses with zero students
    Map<Course, Double> getAverageGradePerCourse();              // avg grade per course
}
