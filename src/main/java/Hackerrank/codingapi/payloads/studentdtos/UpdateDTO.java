package Hackerrank.codingapi.payloads.studentdtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UpdateDTO {
   @NotBlank
   private  String name;
   @NotBlank
    private  String email;
}
