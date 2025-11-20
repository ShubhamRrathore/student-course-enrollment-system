package Hackerrank.codingapi.controllers;


import Hackerrank.codingapi.Service.services.RoleService;
import Hackerrank.codingapi.payloads.rolesdtos.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/role/")
@RequiredArgsConstructor
public class RoleController {

    private  final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> roleDTOResponseEntity(@RequestBody RoleDTO roleDTO){
        return new ResponseEntity<>(this.roleService.createRole(roleDTO) , HttpStatus.OK);
    }
}
