package Hackerrank.codingapi.payloads.enrollmentdtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CreateEnrollDTO {
    private  long id;
    private  long studentId;
    private long courseId;
    private LocalDate enrollmentDate;
    private String grade;
}
