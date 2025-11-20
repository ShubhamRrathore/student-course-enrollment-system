package Hackerrank.codingapi.controllers;

import Hackerrank.codingapi.payloads.coursedtos.CourseCountRespons;
import Hackerrank.codingapi.payloads.coursedtos.CourseDTO;
import Hackerrank.codingapi.Service.Impl.CourseServiceImpl;
import Hackerrank.codingapi.entities.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/course")
public class CourseController {

   private final CourseServiceImpl courseService;

   @PostMapping
   public ResponseEntity<Course> createCourse(@RequestBody Course course){
       return new ResponseEntity<>(this.courseService.createCourse(course), HttpStatus.CREATED);
   }

    @PostMapping("/bulk")
    public ResponseEntity<List<CourseDTO>> createCourses(@RequestBody List<CourseDTO> courses) {
        return new ResponseEntity<List<CourseDTO>>(this.courseService.createCourses(courses),HttpStatus.CREATED);
    }

   @GetMapping
   public  ResponseEntity<List<CourseDTO>> getAllCourse(){
       return new ResponseEntity<>(this.courseService.getAllCourses(),HttpStatus.OK);
   }

   @GetMapping("/")
   public  ResponseEntity<List<CourseCountRespons>> getCoursesWithMinStudents(@RequestParam ("minStudent") Long minStudent)
   {
       return new  ResponseEntity<>(this.courseService.getCoursesWithMinStudents(minStudent) , HttpStatus.OK);
   }

   @GetMapping("/noStudents")
    public ResponseEntity<List<CourseCountRespons>> getCoursesWithNoStudents(){
       return new ResponseEntity<>(this.courseService.getCoursesWithNoStudents(),HttpStatus.OK);
   }

}
