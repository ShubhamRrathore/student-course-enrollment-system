



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
        log.info("POST /api/student - Create student request received");
        Student createdStudent = studentService.createStudent(student);

        log.info("Student created successfully with id={}", createdStudent.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateDTO> updateStudent(@PathVariable Long id , @RequestBody UpdateDTO updateDTO ){
        log.info("PUT /api/student/{} - Update request received", id);
        log.debug("Update payload for studentId={}", id);

        UpdateDTO updatedStudent = studentService.updateStudent(id, updateDTO);

        log.info("Student updated successfully with id={}", id);

        return ResponseEntity.ok(updatedStudent);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatchDTO>  partialUpdate(@PathVariable Long id , @RequestBody PatchDTO patchDTO){
        log.info("PATCH /api/student/{} - Partial update request received", id);
        log.debug("Patch payload received for studentId={}", id);

        PatchDTO patchedStudent = studentService.partialUpdate(id, patchDTO);

        log.info("Student partially updated successfully with id={}", id);

        return ResponseEntity.ok(patchedStudent);
    }



    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        log.info("GET /api/student - Fetch all students");

        List<StudentDTO> students = studentService.getAllStudents();

        log.info("Fetched {} students", students.size());

        return ResponseEntity.ok(students);
    }

    @GetMapping("/enrolled-after")
    public ResponseEntity<List<StudentDTO>> getStudentsEnrolledAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        log.info("GET /api/student/enrolled-after?date={} - Request received", localDate);

        List<StudentDTO> students = studentService.getStudentsEnrolledAfter(localDate);

        log.info("Found {} students enrolled after {}", students.size(), localDate);

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable Long id) {

        log.info("GET /api/student/{} - Fetch student by id", id);

        StudentDTO student = studentService.getStudentById(id);

        log.info("Student fetched successfully with id={}", id);

        return ResponseEntity.ok(student);
    }





}
