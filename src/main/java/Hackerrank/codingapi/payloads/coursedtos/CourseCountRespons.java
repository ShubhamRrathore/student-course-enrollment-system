package Hackerrank.codingapi.payloads.coursedtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CourseCountRespons {

    private Long courseId;
    private String title;
    private Long studentCount;


}
