package hufslikelion.debugging.controller;

import hufslikelion.debugging.common.ApiResponse;
import hufslikelion.debugging.domain.Enrollment;
import hufslikelion.debugging.dto.request.EnrollmentRequest;
import hufslikelion.debugging.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "수강신청", description = "수강신청과 학생별 신청 내역 조회 API")
@RestController
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(summary = "수강신청", description = "학생이 강의를 신청합니다. 정원이 가득 찼거나 이미 신청한 강의면 409를 반환합니다.")
    @PostMapping("/enrollments")
    public ApiResponse<Enrollment> enroll(@Valid @RequestBody EnrollmentRequest request) {
        return ApiResponse.ok(enrollmentService.enroll(request));
    }

    @Operation(summary = "학생별 신청 내역 조회", description = "학생 id로 해당 학생의 수강신청 내역을 조회합니다.")
    @GetMapping("/students/{studentId}/enrollments")
    public ApiResponse<List<Enrollment>> findByStudent(
            @Parameter(description = "학생 id", example = "1") @PathVariable Long studentId) {
        return ApiResponse.ok(enrollmentService.findByStudent(studentId));
    }
}
