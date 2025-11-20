package Hackerrank.codingapi.mapper;

import Hackerrank.codingapi.entities.Role;
import Hackerrank.codingapi.payloads.rolesdtos.UserRoleDTO;
import Hackerrank.codingapi.payloads.userdtos.UserDTO;
import Hackerrank.codingapi.entities.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel  = "spring")
public interface UserMapper {


    UserDTO userToDto(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> DTO_LIST(List<User> userList);


    Role ROLE(UserRoleDTO roleDTO);
    UserRoleDTO UserRoleDTO(Role role);

    Set<User> USER_SET (List<UserRoleDTO> userRoleDTOS);

}
