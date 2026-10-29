package hufslikelion.debugging.repository;

import hufslikelion.debugging.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
