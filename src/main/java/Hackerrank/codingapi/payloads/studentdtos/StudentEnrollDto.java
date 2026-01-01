package Hackerrank.codingapi.payloads.studentdtos;

import Hackerrank.codingapi.payloads.coursedtos.CourseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.PrivateKey;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentEnrollDto {
private Long id;
private LocalDate enrollmentDate;
private CourseDTO courseDTO;
}
