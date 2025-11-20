package Hackerrank.codingapi.Service.Impl;

import Hackerrank.codingapi.entities.Role;
import Hackerrank.codingapi.mapper.UserMapper;
import Hackerrank.codingapi.payloads.userdtos.UserDTO;
import Hackerrank.codingapi.Service.services.UserService;
import Hackerrank.codingapi.entities.User;
import Hackerrank.codingapi.repositories.RoleRepo;
import Hackerrank.codingapi.repositories.UserRepository;
import jakarta.persistence.SecondaryTable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserMapper userMapper;
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        Set<Role> set = userDTO.getRoles().stream().map(roleDTO -> this.roleRepo.findById(roleDTO.getId()).orElseThrow(()-> new RuntimeException("Role not found: " + roleDTO.getId()))).collect(Collectors.toSet());

        User user = userMapper.toEntity(userDTO);
        user.setRoles(set);
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        user =  this.userRepository.save(user);
        return userMapper.userToDto(user) ;
    }



    @Override
    public List<UserDTO> getUser() {
        return List.of();
    }

    @Async
    @Override
    public void sendEmail() {
        System.out.println("Async method running on: " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Email sent successfully!");
    }
}
