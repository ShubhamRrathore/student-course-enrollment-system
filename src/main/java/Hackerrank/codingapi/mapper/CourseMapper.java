package Hackerrank.codingapi.mapper;

import Hackerrank.codingapi.payloads.coursedtos.CourseCountDTO;
import Hackerrank.codingapi.payloads.coursedtos.CourseCountRespons;
import Hackerrank.codingapi.payloads.coursedtos.CourseDTO;
import Hackerrank.codingapi.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface CourseMapper {
    CourseDTO toDTO(Course course);
    Course   toEntity(CourseDTO courseDTO);

    CourseCountRespons COURSE_COUNT_RESPONS(CourseCountDTO courseCountDTO);
}
