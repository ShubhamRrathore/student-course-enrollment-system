package Hackerrank.codingapi.securityconfig;

import Hackerrank.codingapi.payloads.authdtos.CustomUserDetails;
import Hackerrank.codingapi.entities.User;
import Hackerrank.codingapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

   private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("print something please +++++ {}" , username);
//        User user = userRepository.findByUserEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found Exception" , "User" , 1));

        Optional<User> userOpt = userRepository.findByUserEmail(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            log.info("Fetched User: {}", user);
            return new CustomUserDetails(user);
        } else {
            log.warn("No user found with email: {}", username);
            throw new UsernameNotFoundException("User not found with email: " + username);
        }


//        System.out.println("DEBUG >>> reached here with username: " + username);

//        log.info("Print user object {}" , user.toString());
//        return new CustomUserDetails(user);
    }
}
