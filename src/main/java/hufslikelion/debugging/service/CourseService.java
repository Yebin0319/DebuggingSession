package hufslikelion.debugging.service;

import hufslikelion.debugging.domain.Course;
import hufslikelion.debugging.dto.request.CourseCreateRequest;
import hufslikelion.debugging.dto.response.CourseResponse;
import hufslikelion.debugging.repository.CourseRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseResponse> findAll() {
        return courseRepository.findAll().stream().map(CourseResponse::from).toList();
    }

    public CourseResponse find(Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        return CourseResponse.from(course);
    }

    @Transactional
    public CourseResponse create(CourseCreateRequest request) {
        Course course = new Course(request.getName(), request.getCapacity(), request.getDayOfWeek());
        return CourseResponse.from(courseRepository.save(course));
    }
}
