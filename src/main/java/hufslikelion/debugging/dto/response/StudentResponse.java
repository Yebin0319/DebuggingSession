package hufslikelion.debugging.dto.response;

import hufslikelion.debugging.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private String name;

    public static StudentResponse from(Student student) {
        return new StudentResponse(student.getId(), student.getName());
    }
}
