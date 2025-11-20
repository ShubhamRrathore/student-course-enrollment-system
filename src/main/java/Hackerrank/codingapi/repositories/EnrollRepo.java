package Hackerrank.codingapi.repositories;

import Hackerrank.codingapi.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepo extends JpaRepository<Enrollment,Long> {
}
