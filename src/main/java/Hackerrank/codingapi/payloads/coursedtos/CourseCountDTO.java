package Hackerrank.codingapi.payloads.coursedtos;

import Hackerrank.codingapi.entities.Course;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CourseCountDTO {
    private Long courseId;
    private String title;
    private Long studentCount;

}
