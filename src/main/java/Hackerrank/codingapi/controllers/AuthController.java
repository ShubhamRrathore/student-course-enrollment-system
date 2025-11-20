package Hackerrank.codingapi.controllers;

import Hackerrank.codingapi.mapper.UserMapper;
import Hackerrank.codingapi.payloads.authdtos.CustomUserDetails;
import Hackerrank.codingapi.payloads.authdtos.JwtAuthRequest;
import Hackerrank.codingapi.payloads.authdtos.JwtAuthResponse;
import Hackerrank.codingapi.securityconfig.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenHelper jwtTokenHelper;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {

//            // Directly authenticate
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
//            );
           Authentication authentication =  this.authenticate(request.getUserName(),request.getPassword());

            // Agar authenticate successful → Authentication object me details milega
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("Please print the userDetails {}" , userDetails);

            // Token generate
            String token = jwtTokenHelper.generateToken(userDetails);

            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(token);
//            response.setUser(this.mapper.map((User) userDetails, UserDto.class));
            response.setUser(this.userMapper.userToDto(userDetails.getUser()));
            return ResponseEntity.ok(response);

    }


    private Authentication authenticate(String username, String password) {

        return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
//        try {
//           return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//            throw new ResponseStatusException(
//                    HttpStatus.UNAUTHORIZED, "Invalid username or password", ex
//            );
//        } catch (Exception ex) {
//            throw new ResponseStatusException(
//                    HttpStatus.INTERNAL_SERVER_ERROR, "Authentication failed", ex
//            );
//        }
    }}
