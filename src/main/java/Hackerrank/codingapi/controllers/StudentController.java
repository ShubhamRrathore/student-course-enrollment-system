package Hackerrank.codingapi.controllers;

import Hackerrank.codingapi.payloads.studentdtos.StudentDTO;
import Hackerrank.codingapi.Service.services.StudentService;
import Hackerrank.codingapi.entities.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<Student>(this.studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<StudentDTO>> getAllStudent(){
        return  new ResponseEntity<>(this.studentService.getAllStudents(),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>>  getStudentsEnrolledAfter( @RequestParam ("localDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate){

        System.out.println("bhai kcuh to print ker de kya issue hai");
        return new  ResponseEntity<>(this.studentService.getStudentsEnrolledAfter(localDate) ,HttpStatus.OK);
    }




}
