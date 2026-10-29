package hufslikelion.debugging.dto.response;

import hufslikelion.debugging.domain.Enrollment;
import hufslikelion.debugging.domain.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {

    private Long id;
    private String studentName;
    private String courseName;
    private EnrollmentStatus status;

    public static EnrollmentResponse from(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudent().getName(),
                enrollment.getCourse().getName(),
                enrollment.getStatus());
    }
}
