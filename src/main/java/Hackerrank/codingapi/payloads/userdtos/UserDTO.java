package Hackerrank.codingapi.payloads.userdtos;

import Hackerrank.codingapi.entities.Role;
import Hackerrank.codingapi.payloads.rolesdtos.UserRoleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private  Integer id;
    @Email
    private String userEmail;

    @NotBlank
    private String userName;

    private String about;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<UserRoleDTO> roles = new HashSet<>();


}
