package Hackerrank.codingapi.repositories;

import Hackerrank.codingapi.entities.Course;
import Hackerrank.codingapi.payloads.coursedtos.CourseCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {


    // Courses with minimum students
    @Query("""
           SELECT new Hackerrank.codingapi.payloads.coursedtos.CourseCountDTO(c.id, c.title, COUNT(e))
           FROM Course c
           LEFT JOIN c.enrollments e
           GROUP BY c.id, c.title
           HAVING COUNT(e) > :minStudents
           """)
    List<CourseCountDTO> getCoursesWithMinStudents(long minStudents);

    // Courses with no students
    @Query("""
           SELECT new Hackerrank.codingapi.payloads.coursedtos.CourseCountDTO(c.id, c.title, COUNT(e))
           FROM Course c
           LEFT JOIN c.enrollments e
           GROUP BY c.id, c.title
           HAVING COUNT(e) = 0
           """)
    List<CourseCountDTO> getCoursesWithNoStudents();

    boolean existsByTitle(String title);



}
