package hufslikelion.debugging.service;

import hufslikelion.debugging.common.BusinessException;
import hufslikelion.debugging.domain.Course;
import hufslikelion.debugging.domain.Enrollment;
import hufslikelion.debugging.domain.Student;
import hufslikelion.debugging.dto.request.EnrollmentRequest;
import hufslikelion.debugging.repository.CourseRepository;
import hufslikelion.debugging.repository.EnrollmentRepository;
import hufslikelion.debugging.repository.StudentRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Enrollment enroll(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND, "학생을 찾을 수 없습니다. id=" + request.getStudentId()));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND, "강의를 찾을 수 없습니다. id=" + request.getCourseId()));

        long enrolledCount = enrollmentRepository.countByCourseId(course.getId());
        if (enrolledCount >= course.getCapacity()) {
            throw new BusinessException(HttpStatus.CONFLICT, "정원이 초과되었습니다.");
        }
        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), course.getId())) {
            throw new BusinessException(HttpStatus.CONFLICT, "이미 신청한 강의입니다.");
        }

        return enrollmentRepository.save(new Enrollment(student, course));
    }

    public List<Enrollment> findByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}
