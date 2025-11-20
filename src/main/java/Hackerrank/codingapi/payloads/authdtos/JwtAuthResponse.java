package Hackerrank.codingapi.payloads.authdtos;

import Hackerrank.codingapi.payloads.userdtos.UserDTO;
import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;

    private UserDTO user;
}
