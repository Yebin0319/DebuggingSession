package hufslikelion.debugging.repository;

import hufslikelion.debugging.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
