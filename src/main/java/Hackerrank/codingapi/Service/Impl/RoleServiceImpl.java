package Hackerrank.codingapi.Service.Impl;

import Hackerrank.codingapi.Service.services.RoleService;
import Hackerrank.codingapi.Utils.ValidationUtils;
import Hackerrank.codingapi.entities.Role;
import Hackerrank.codingapi.mapper.RoleMapper;
import Hackerrank.codingapi.payloads.rolesdtos.RoleDTO;
import Hackerrank.codingapi.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;
    private final RoleMapper roleMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {

        // Service layer validation
        ValidationUtils.validateNotNull(roleDTO.getName(), "RoleName");

        if(this.roleRepo.existsByName(roleDTO.getName())) {
            throw new RuntimeException("Role already exists!");
        }
        Role role = this.roleMapper.toEntity(roleDTO);
      role =   this.roleRepo.save(role);
      return this.roleMapper.toDTO(role);
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public List<RoleDTO> getAllRole() {
        return null;
    }

    @Override
    public RoleDTO deleteRole(RoleDTO roleDTO) {
        return null;
    }
}
