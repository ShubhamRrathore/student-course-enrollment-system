package Hackerrank.codingapi.mapper;

import Hackerrank.codingapi.entities.Role;
import Hackerrank.codingapi.payloads.rolesdtos.RoleDTO;
import org.mapstruct.Mapper;
import org.springframework.context.support.BeanDefinitionDsl;

@Mapper(componentModel = "spring" )
public interface RoleMapper {

    RoleDTO toDTO(Role role);
    Role toEntity (RoleDTO roleDTO);

}
