package Hackerrank.codingapi.payloads.authdtos;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userName;
    private  String password;
}
