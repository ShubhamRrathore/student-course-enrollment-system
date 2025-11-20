package Hackerrank.codingapi.controllers;


import Hackerrank.codingapi.payloads.enrollmentdtos.GetAllEnrollDTO;
import Hackerrank.codingapi.payloads.enrollmentdtos.StudentHelperEnrollDto;
import Hackerrank.codingapi.payloads.enrollmentdtos.CreateEnrollDTO;
import Hackerrank.codingapi.Service.services.EnrollService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api/enroll/")
@RequiredArgsConstructor
public class EnrollController {
    private  final EnrollService enrollService;
    private static final Logger logger = LoggerFactory.getLogger(EnrollController.class);

    @PostMapping
    public ResponseEntity<CreateEnrollDTO> CreateEnroll(@RequestBody StudentHelperEnrollDto enrollmentDto){
        return new ResponseEntity<>(this.enrollService.enrollStudentInCourse(
                enrollmentDto.getStudentId(),
                enrollmentDto.getCourseId()
        ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetAllEnrollDTO>> getAllEnrollment(){
        return new  ResponseEntity<>(this.enrollService.getAllEnrollments(), HttpStatus.OK);

    }





}
