package Hackerrank.codingapi.controllers;


import Hackerrank.codingapi.payloads.enrollmentdtos.GetAllEnrollDTO;
import Hackerrank.codingapi.payloads.enrollmentdtos.StudentHelperEnrollDto;
import Hackerrank.codingapi.payloads.enrollmentdtos.CreateEnrollDTO;
import Hackerrank.codingapi.Service.services.EnrollService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/enroll/")
@RequiredArgsConstructor
public class EnrollController {
    private  final EnrollService enrollService;
    private static final Logger logger = LoggerFactory.getLogger(EnrollController.class);
    // ---------------- ENROLL STUDENT IN COURSE ----------------
    @PostMapping
    public ResponseEntity<CreateEnrollDTO> createEnroll(
            @RequestBody StudentHelperEnrollDto enrollmentDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        enrollService.enrollStudentInCourse(
                                enrollmentDto.getStudentId(),
                                enrollmentDto.getCourseId()
                        )
                );
    }

    // ---------------- GET ALL ENROLLMENTS ----------------
    @GetMapping
    public ResponseEntity<List<GetAllEnrollDTO>> getAllEnrollment() {
        return ResponseEntity.ok(enrollService.getAllEnrollments());
    }



}
