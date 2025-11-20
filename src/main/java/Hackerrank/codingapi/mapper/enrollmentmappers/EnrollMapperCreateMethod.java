package Hackerrank.codingapi.mapper.enrollmentmappers;

import Hackerrank.codingapi.payloads.enrollmentdtos.CreateEnrollDTO;
import Hackerrank.codingapi.entities.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface EnrollMapperCreateMethod {
    @Mapping(source = "course.id"  , target = "courseId")
    @Mapping(source = "student.id" , target = "studentId")
    CreateEnrollDTO enrollToDTO(Enrollment enrollment);



}
