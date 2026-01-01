package Hackerrank.codingapi.controllers;

import Hackerrank.codingapi.mapper.UserMapper;
import Hackerrank.codingapi.payloads.authdtos.CustomUserDetails;
import Hackerrank.codingapi.payloads.authdtos.JwtAuthRequest;
import Hackerrank.codingapi.payloads.authdtos.JwtAuthResponse;
import Hackerrank.codingapi.securityconfig.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenHelper jwtTokenHelper;
    private final UserMapper userMapper;

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(
            @RequestBody JwtAuthRequest request) {

        Authentication authentication = authenticate(
                request.getUserName(),
                request.getPassword()
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        log.info("User authenticated successfully: {}",
                userDetails.getUsername());

        String token = jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setUser(userMapper.userToDto(userDetails.getUser()));

        return ResponseEntity.ok(response);
    }

    // ---------------- AUTHENTICATION ----------------
    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException ex) {
            log.warn("Invalid login attempt for username: {}", username);
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password"
            );
        } catch (Exception ex) {
            log.error("Authentication failed for username: {}", username, ex);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Authentication failed"
            );
        }
    }

    }
