package Hackerrank.codingapi.controllers;


import Hackerrank.codingapi.payloads.userdtos.UserDTO;
import Hackerrank.codingapi.Service.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/v1/user")
@PreAuthorize("hasRole('MANAGER')")
public class UserEntityController {

   private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO){
        log.info("please print the userDto {}" ,userDTO);
        UserDTO userDTO1 = this.userService.createUser(userDTO);
        return new ResponseEntity<UserDTO>(userDTO1,HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('MANAGER')")
    public String adminAccess() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());
        return "Welcome Admin!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/send")
    public ResponseEntity<String> sendEmail() {
        this.userService.sendEmail(); // runs asynchronously
        return new  ResponseEntity<>("\"Request accepted, email sending in background!\"" ,HttpStatus.OK);
    }

//
//    @GetMapping3
//    public ResponseEntity<String> stringResponseEntity(){
//        return new ResponseEntity<>("Welcome admin" , HttpStatus.OK);
//    }



}
