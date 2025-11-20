package Hackerrank.codingapi.Service.services;

import Hackerrank.codingapi.payloads.studentdtos.StudentDTO;
import Hackerrank.codingapi.entities.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StudentService {


    // -----------------------
    // Basic CRUD
    // -----------------------
    Student createStudent(Student student);
    List<StudentDTO> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);

    // -----------------------
    // Student Specific Queries
    // -----------------------
    List<StudentDTO> getStudentsEnrolledAfter(LocalDate date);          // students enrolled after a given date
    Map<Student, Long> getCourseCountPerStudent();                   // number of courses per student
    List<Student> getTopNStudentsWithMostCourses(int n);             // top N students by course count
}