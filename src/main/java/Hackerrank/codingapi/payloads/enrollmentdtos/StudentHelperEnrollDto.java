package Hackerrank.codingapi.payloads.enrollmentdtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentHelperEnrollDto {

    private Long courseId;
    private Long studentId;
}
