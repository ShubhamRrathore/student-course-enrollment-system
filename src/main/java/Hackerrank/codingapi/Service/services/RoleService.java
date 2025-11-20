package Hackerrank.codingapi.Service.services;

import Hackerrank.codingapi.payloads.rolesdtos.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(RoleDTO roleDTO);
    List<> getAll();
    RoleDTO deleteRole(RoleDTO roleDTO);
}
