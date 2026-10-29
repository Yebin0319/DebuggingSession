package hufslikelion.debugging.controller;

import hufslikelion.debugging.common.ApiResponse;
import hufslikelion.debugging.domain.Enrollment;
import hufslikelion.debugging.dto.request.EnrollmentRequest;
import hufslikelion.debugging.service.EnrollmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enrollments")
    public ApiResponse<Enrollment> enroll(@Valid @RequestBody EnrollmentRequest request) {
        return ApiResponse.ok(enrollmentService.enroll(request));
    }

    @GetMapping("/students/{studentId}/enrollments")
    public ApiResponse<List<Enrollment>> findByStudent(@PathVariable Long studentId) {
        return ApiResponse.ok(enrollmentService.findByStudent(studentId));
    }
}
