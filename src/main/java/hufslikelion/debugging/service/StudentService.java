package hufslikelion.debugging.service;

import hufslikelion.debugging.domain.Student;
import hufslikelion.debugging.dto.request.StudentCreateRequest;
import hufslikelion.debugging.dto.response.StudentResponse;
import hufslikelion.debugging.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentResponse create(StudentCreateRequest request) {
        Student student = new Student(request.getName());
        return StudentResponse.from(studentRepository.save(student));
    }
}
