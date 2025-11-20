package Hackerrank.codingapi.payloads.enrollmentdtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GetAllEnrollDTO {
    private Long id;
    private LocalDate enrollmentDate;
    private Long courseId;
    private String courseTitle;
    private Long studentId;
    private String studentName;
}
