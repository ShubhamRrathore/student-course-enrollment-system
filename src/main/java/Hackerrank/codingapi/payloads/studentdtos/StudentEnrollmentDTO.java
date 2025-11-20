package Hackerrank.codingapi.payloads.studentdtos;

import Hackerrank.codingapi.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class StudentEnrollmentDTO {
    private Student student;
    private LocalDate localDate;
}
