



package Hackerrank.codingapi.controllers;

import Hackerrank.codingapi.payloads.studentdtos.PatchDTO;
import Hackerrank.codingapi.payloads.studentdtos.StudentDTO;
import Hackerrank.codingapi.Service.services.StudentService;
import Hackerrank.codingapi.entities.Student;
import Hackerrank.codingapi.payloads.studentdtos.UpdateDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/student")
@SecurityRequirement(name = "bearerAuth")

public class StudentController {

    private final StudentService studentService;


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateDTO> updateStudent(@PathVariable Long id , @RequestBody UpdateDTO updateDTO ){
        return ResponseEntity.ok(studentService.updateStudent(id, updateDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatchDTO>  partialUpdate(@PathVariable Long id , @RequestBody PatchDTO patchDTO){
        return ResponseEntity.ok(studentService.partialUpdate(id , patchDTO));
    }



    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/enrolled-after")
    public ResponseEntity<List<StudentDTO>> getStudentsEnrolledAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return ResponseEntity.ok(studentService.getStudentsEnrolledAfter(localDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }





}
