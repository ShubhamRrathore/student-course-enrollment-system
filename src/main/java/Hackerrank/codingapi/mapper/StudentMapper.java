package Hackerrank.codingapi.mapper;

import Hackerrank.codingapi.payloads.enrollmentdtos.GetAllEnrollDTO;
import Hackerrank.codingapi.payloads.studentdtos.StudentDTO;
import Hackerrank.codingapi.payloads.studentdtos.StudentEnrollDto;
import Hackerrank.codingapi.payloads.studentdtos.StudentEnrollmentDTO;
import Hackerrank.codingapi.entities.Enrollment;
import Hackerrank.codingapi.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
 @Mapping(source = "enrollments", target = "enrollData")
 StudentDTO studentoDTO(Student student);
//
//@Mapping(source = "course.id"  , target = "courseId")
//@Mapping(source = "course.title" , target = "courseTitle")
//@Mapping(source = "student.id", target = "studentId")
//@Mapping(source = "student.name", target = "studentName")
//@Mapping(source = "course" , target = "courseDTO")
//    GetAllEnrollDTO ENROLL_DTO(Enrollment enrollment);
//List<GetAllEnrollDTO> toEnrollDTOs(List<Enrollment> enrollments);

    @Mapping(source = "course" , target = "courseDTO")
    StudentEnrollDto ENROLL_DTO(Enrollment enrollment);
   List<StudentEnrollDto> toEnrollDTOs(List<Enrollment> enrollments);

    // 3️⃣ StudentEnrollmentDTO → StudentDTOs
    // yaha "student" object ke andar actual student data hai
    @Mapping(source = "student.enrollments", target = "enrollData")
    @Mapping(source = "student.id", target = "id")
    @Mapping(source = "student.name", target = "name")
    StudentDTO toStudentDTO(StudentEnrollmentDTO studentEnrollmentDTO);

    List<StudentDTO> toStudentDTOs(List<StudentEnrollmentDTO> studentEnrollmentDTOs);



}
