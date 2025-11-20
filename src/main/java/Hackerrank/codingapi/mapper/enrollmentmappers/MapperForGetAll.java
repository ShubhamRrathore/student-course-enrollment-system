package Hackerrank.codingapi.mapper.enrollmentmappers;


import Hackerrank.codingapi.payloads.enrollmentdtos.GetAllEnrollDTO;
import Hackerrank.codingapi.entities.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface MapperForGetAll {
    @Mapping(source = "course.id"  , target = "courseId")
    @Mapping(source = "student.id" , target = "studentId")
    @Mapping(source = "course.title" , target = "courseTitle")
    @Mapping(source = "student.name." , target = "studentName")
    GetAllEnrollDTO enrollToDTO(Enrollment enrollment);
}
