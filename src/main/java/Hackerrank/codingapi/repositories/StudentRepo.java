package Hackerrank.codingapi.repositories;

import Hackerrank.codingapi.payloads.studentdtos.StudentEnrollmentDTO;
import Hackerrank.codingapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.enrollments e LEFT JOIN FETCH e.course")
    List<Student> findAllWithEnrollments();

//    @Query("SELECT e.student FROM Enrollment e WHERE e.enrollmentDate > : date")
//    List<Student> getStudentsEnrolledAfter(@Param("date") LocalDate Date);

    @Query("SELECT new Hackerrank.codingapi.payloads.studentdtos.StudentEnrollmentDTO(e.student, e.enrollmentDate) " +
            "FROM Enrollment e WHERE e.enrollmentDate > :date")
    List<StudentEnrollmentDTO> getStudentsEnrolledAfter(@Param("date") LocalDate date);
}
