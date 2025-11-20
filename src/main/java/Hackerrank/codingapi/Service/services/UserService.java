package Hackerrank.codingapi.Service.services;


import Hackerrank.codingapi.payloads.userdtos.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getUser();
    void sendEmail();

}
