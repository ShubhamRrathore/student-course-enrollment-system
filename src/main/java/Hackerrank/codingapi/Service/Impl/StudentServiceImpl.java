package Hackerrank.codingapi.Service.Impl;

import Hackerrank.codingapi.Utils.ValidationUtils;
import Hackerrank.codingapi.exception.ResourceNotFoundException;
import Hackerrank.codingapi.mapper.StudentMapper;
import Hackerrank.codingapi.payloads.studentdtos.PatchDTO;
import Hackerrank.codingapi.payloads.studentdtos.StudentDTO;

import Hackerrank.codingapi.payloads.studentdtos.StudentEnrollmentDTO;
import Hackerrank.codingapi.Service.services.StudentService;
import Hackerrank.codingapi.controllers.EnrollController;
import Hackerrank.codingapi.entities.Student;
import Hackerrank.codingapi.payloads.studentdtos.UpdateDTO;
import Hackerrank.codingapi.repositories.StudentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollController.class);

    private final StudentMapper studentMapper;
    private  final StudentRepo studentRepo;
    @Override
    public Student createStudent(Student student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        return studentRepo.save(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        try{
            List<Student> students = this.studentRepo.findAllWithEnrollments();
//            List<Student> students = this.studentRepo.findAll();

//            for(Student  s: students){
//                System.out.println("Student: " + s.getName());
//                for(Enrollment enrollment: s.getEnrollments()){
//                    System.out.println("   Course: " + enrollment.getCourse());
//                }
//            }
          return  students.stream().map(studentMapper :: studentoDTO).toList();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        ValidationUtils.validateNotNull(id, "id");
       Student student = studentRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Student"  , "Id" , id));
       return studentMapper.studentoDTO(student);
    }

    @Transactional
    @Override
    public UpdateDTO updateStudent(Long id, UpdateDTO updateDTO) {
        ValidationUtils.validateNotNull(id, "id");
        Student existingStudent  = studentRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Student"  , "Id" , id));
        existingStudent.setEmail(updateDTO.getEmail());
        existingStudent.setName(updateDTO.getName());
        // dirty checking will work and auto save it

//        Student student1 = studentRepo.save(existingStudent);
//        student1.getEnrollments().size();
//
//        System.out.println("please print student1 :::" + student1);
//        System.out.println("please print existingStudent1 :::" + existingStudent);

        return studentMapper.UPDATE_DTO(existingStudent);
    }

    @Override
    @Transactional
    public PatchDTO partialUpdate( Long id , PatchDTO patchDTO) {
        ValidationUtils.validateNotNull(id, "id");
        Student existingStudent  = studentRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Student"  , "Id" , id));
        if (patchDTO.getName() != null) {
            existingStudent.setName(patchDTO.getName());
        }
        if (patchDTO.getEmail() != null) {
            existingStudent.setEmail(patchDTO.getEmail());
        }
        return  studentMapper.PATCH_DTO(existingStudent);
    }




    @Override
    public void deleteStudent(Long id) {
    }

    @Override
    public List<StudentDTO> getStudentsEnrolledAfter(LocalDate date) {

        try {
            List<StudentEnrollmentDTO>  studentEnrollmentDTO =   this.studentRepo.getStudentsEnrolledAfter(date);
         return   studentEnrollmentDTO.stream().map(studentMapper :: toStudentDTO ).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Student, Long> getCourseCountPerStudent() {
        return Map.of();
    }

    @Override
    public List<Student> getTopNStudentsWithMostCourses(int n) {
        return List.of();
    }
}
