package Hackerrank.codingapi.payloads.studentdtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchDTO {
    private Long id;
    private  String name;
    private  String email;
}
