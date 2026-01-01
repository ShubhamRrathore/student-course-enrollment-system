package Hackerrank.codingapi.Service.services;

import Hackerrank.codingapi.payloads.studentdtos.PatchDTO;
import Hackerrank.codingapi.payloads.studentdtos.StudentDTO;
import Hackerrank.codingapi.entities.Student;
import Hackerrank.codingapi.payloads.studentdtos.UpdateDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StudentService {


    // -----------------------
    // Basic CRUD
    // -----------------------
    Student createStudent(Student student);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    UpdateDTO updateStudent(Long id, UpdateDTO updateDTO);
    PatchDTO partialUpdate(Long id, PatchDTO patchDTO);
    void deleteStudent(Long id);

    // -----------------------
    // Student Specific Queries
    // -----------------------
    List<StudentDTO> getStudentsEnrolledAfter(LocalDate date);          // students enrolled after a given date
    Map<Student, Long> getCourseCountPerStudent();                   // number of courses per student
    List<Student> getTopNStudentsWithMostCourses(int n);             // top N students by course count
}