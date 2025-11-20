package Hackerrank.codingapi.Service.services;

import Hackerrank.codingapi.payloads.rolesdtos.RoleDTO;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(RoleDTO roleDTO);
    RoleDTO getAll(RoleDTO roleDTO);
    RoleDTO deleteRole(RoleDTO roleDTO);
}
