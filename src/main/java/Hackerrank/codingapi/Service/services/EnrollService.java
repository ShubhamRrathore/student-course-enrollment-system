package Hackerrank.codingapi.Service.services;

import Hackerrank.codingapi.payloads.enrollmentdtos.GetAllEnrollDTO;
import Hackerrank.codingapi.payloads.enrollmentdtos.CreateEnrollDTO;
import Hackerrank.codingapi.entities.Course;
import Hackerrank.codingapi.entities.Enrollment;
import Hackerrank.codingapi.entities.Student;

import java.util.List;

public interface EnrollService {

    // -----------------------
    // Enrollment CRUD
    // -----------------------
    CreateEnrollDTO enrollStudentInCourse(Long studentId, Long courseId); // enroll
    Enrollment updateGrade(Long studentId, Long courseId, String grade);                      // update grade
    void deleteEnrollment(Long enrollmentId);                                                 // unenroll student

    // -----------------------
    // Enrollment Queries
    // -----------------------
    List<GetAllEnrollDTO> getAllEnrollments();                   // list all enrollments
    List<Course> getCoursesOfStudent(Long studentId);        // all courses of a student
    List<Student> getStudentsOfCourse(Long courseId);        // all students in a course
    List<Enrollment> getEnrollmentsOfStudent(Long studentId); // all enrollments for student
    List<Enrollment> getEnrollmentsOfCourse(Long courseId);   // all enrollments for course
    List<Enrollment> getEnrollmentsSortedByDate();           // all enrollments sorted by date

    // -----------------------
    // Advanced Queries
    // -----------------------
    List<Student> getStudentsByCourseAndGrade(String courseTitle, String grade); // all students with grade in course
    List<Student> getCommonStudentsInCourses(Long courseId1, Long courseId2);    // students enrolled in both courses
    List<Student> getStudentsWhoFailedAnyCourse();                               // students with grade = F
}
