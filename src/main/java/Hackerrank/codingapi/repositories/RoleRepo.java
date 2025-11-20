package Hackerrank.codingapi.repositories;

import Hackerrank.codingapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

}
