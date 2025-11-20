package Hackerrank.codingapi.payloads.studentdtos;

import Hackerrank.codingapi.payloads.enrollmentdtos.GetAllEnrollDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private  Long id ;
    private  String name;
    private List<GetAllEnrollDTO>  enrollData;
}
