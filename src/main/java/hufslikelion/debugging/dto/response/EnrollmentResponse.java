package hufslikelion.debugging.dto.response;

import hufslikelion.debugging.domain.Enrollment;
import hufslikelion.debugging.domain.EnrollmentStatus;

public record EnrollmentResponse(Long id, String studentName, String courseName, EnrollmentStatus status) {

    public static EnrollmentResponse from(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudent().getName(),
                enrollment.getCourse().getName(),
                enrollment.getStatus());
    }
}
