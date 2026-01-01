package Hackerrank.codingapi.Service.Impl;

import Hackerrank.codingapi.RabbitMq.MockRabbitMQ.EmailEventPublisher;
import Hackerrank.codingapi.RabbitMq.RabbitConfig;
import Hackerrank.codingapi.Service.services.EnrollService;
import Hackerrank.codingapi.Utils.EmailStatus;
import Hackerrank.codingapi.Utils.ValidationUtils;
import Hackerrank.codingapi.entities.Course;
import Hackerrank.codingapi.entities.EmailRequest;
import Hackerrank.codingapi.entities.Enrollment;
import Hackerrank.codingapi.entities.Student;
import Hackerrank.codingapi.exception.AlreadyEnrolledException;
import Hackerrank.codingapi.exception.ResourceNotFoundException;
import Hackerrank.codingapi.mapper.enrollmentmappers.EnrollMapperCreateMethod;
import Hackerrank.codingapi.mapper.enrollmentmappers.MapperForGetAll;
import Hackerrank.codingapi.payloads.enrollmentdtos.CreateEnrollDTO;
import Hackerrank.codingapi.payloads.enrollmentdtos.GetAllEnrollDTO;
import Hackerrank.codingapi.repositories.CourseRepo;
import Hackerrank.codingapi.repositories.EmailRequestRepository;
import Hackerrank.codingapi.repositories.EnrollRepo;
import Hackerrank.codingapi.repositories.StudentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollServiceImpl.class);
    private final EmailEventPublisher emailEventPublisher;
    private final EnrollRepo enrollRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final EnrollMapperCreateMethod enrollMapper;
    private final MapperForGetAll enrollToDTO;
    private final EmailRequestRepository emailRequestRepository;
    private final RabbitTemplate rabbitTemplate;
    @Override

    @Transactional
    public CreateEnrollDTO enrollStudentInCourse(Long studentId, Long courseId) {
//        if (studentId== null ||  courseId == null) {
//            throw new IllegalArgumentException("StudentId, CourseId and enrollmentDate cannot be null");
//        }

        // Service layer validation
        ValidationUtils.validateNotNull(studentId, "StudentId");
        ValidationUtils.validateNotNull(courseId, "CourseId");

        Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("student", " Id", studentId));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course", "Id" , courseId));


        if (enrollRepo.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new AlreadyEnrolledException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(LocalDate.now(ZoneOffset.UTC));
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment enrollment1 = this.enrollRepo.save(enrollment);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setToAddress(student.getEmail());
        emailRequest.setSubject("Enrollment Confirmation");
        emailRequest.setBody("Hello " + student.getName() + ", you enrolled in " + course.getTitle());
        emailRequest.setStatus(EmailStatus.PENDING);
        emailRequest = emailRequestRepository.save(emailRequest);


         emailEventPublisher.publishEmail(emailRequest.getId());
        // 2️⃣ Send email request ID to queue
//        rabbitTemplate.convertAndSend(
//                RabbitConfig.EMAIL_EXCHANGE,
//                RabbitConfig.EMAIL_ROUTING_KEY,
//                emailRequest.getId()
//        );
        System.out.println("Queued email ID: " + emailRequest.getId());
        return enrollMapper.enrollToDTO(enrollment1);
    }

    @Override
    public Enrollment updateGrade(Long studentId, Long courseId, String grade) {

        return null;

    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {

    }

    @Override
    public List<GetAllEnrollDTO> getAllEnrollments() {

        try
        {
            List<Enrollment> enrollments =  this.enrollRepo.findAll();
            return  enrollments.stream().map(enrollToDTO::enrollToDTO ).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Course> getCoursesOfStudent(Long studentId) {
        return List.of();
    }

    @Override
    public List<Student> getStudentsOfCourse(Long courseId) {
        return List.of();
    }

    @Override
    public List<Enrollment> getEnrollmentsOfStudent(Long studentId) {
        return List.of();
    }

    @Override
    public List<Enrollment> getEnrollmentsOfCourse(Long courseId) {
        return List.of();
    }

    @Override
    public List<Enrollment> getEnrollmentsSortedByDate() {
        return List.of();
    }

    @Override
    public List<Student> getStudentsByCourseAndGrade(String courseTitle, String grade) {
        return List.of();
    }

    @Override
    public List<Student> getCommonStudentsInCourses(Long courseId1, Long courseId2) {
        return List.of();
    }

    @Override
    public List<Student> getStudentsWhoFailedAnyCourse() {
        return List.of();
    }
}
