package Hackerrank.codingapi.Service.Impl;

import Hackerrank.codingapi.Utils.ValidationUtils;
import Hackerrank.codingapi.exception.ValidationException;
import Hackerrank.codingapi.mapper.CourseMapper;
import Hackerrank.codingapi.payloads.coursedtos.CourseCountDTO;
import Hackerrank.codingapi.payloads.coursedtos.CourseCountRespons;
import Hackerrank.codingapi.payloads.coursedtos.CourseDTO;
import Hackerrank.codingapi.Service.services.CourseService;
import Hackerrank.codingapi.entities.Course;
import Hackerrank.codingapi.repositories.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

     private final CourseRepo courseRepo;
     private final CourseMapper courseMapper;

    @Override
    public Course createCourse(Course course) {
        if(course.getTitle() == null || course.getTitle().isBlank()){
            throw new IllegalArgumentException("Course Title can't be Empty");
        }
        return  this.courseRepo.save(course);

    }

    @Override
    public List<CourseDTO> createCourses(List<CourseDTO> courses) {

      courses.forEach(courseDTO -> {ValidationUtils.validateNotBlank(courseDTO.getTitle(),"Course title");
          if (courseRepo.existsByTitle(courseDTO.getTitle())) {
              throw new ValidationException("Course with title '" + courseDTO.getTitle() + "' already exists");
          }
      });

//        return courses.stream()
//                .map(courseMapper::toEntity)   // DTO -> Entity
//                .map(courseRepo::save)         // save each entity (DB call per item)
//                .map(courseMapper::toDto)      // Entity -> DTO
//                .toList();

        // Step 1: DTO -> Entity convert karo
        List<Course> courseEntities = courses.stream()
                .filter(courseDTO -> !courseRepo.existsByTitle(courseDTO.getTitle()))
                .map(courseMapper::toEntity)
                .toList();

        // Step 2: saari entities ek saath save karo
        List<Course> savedCourses = courseRepo.saveAll(courseEntities);

        // Step 3: Entity -> DTO convert karke return karo
        return savedCourses.stream()
                .map(courseMapper::toDTO)
                .toList();
    }


    @Override
    public List<CourseDTO> getAllCourses() {
            List<Course> course =  this.courseRepo.findAll();
          return  course.stream().map(courseMapper ::toDTO ).toList();
    }

    @Override
    public Course getCourseById(Long id) {


        return null;
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {

    }


    @Override
    public List<CourseCountRespons> getCoursesWithMinStudents(Long minStudents) {

            if (minStudents == null || minStudents < 0) {
                throw new IllegalArgumentException("minStudents must not be null or negative");
            }
            List<CourseCountDTO> courseDTO = this.courseRepo.getCoursesWithMinStudents(minStudents);
            return courseDTO.stream().map(courseMapper ::COURSE_COUNT_RESPONS ).toList();
    }

    @Override
    public List<CourseCountRespons> getCoursesWithNoStudents() {

        List<CourseCountDTO> courseCountDTOS = this.courseRepo.getCoursesWithNoStudents();
        return courseCountDTOS.stream().map(courseMapper :: COURSE_COUNT_RESPONS).toList();
    }

    @Override
    public Map<Course, Double> getAverageGradePerCourse() {
        return Map.of();
    }
}
