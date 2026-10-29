package hufslikelion.debugging.controller;

import hufslikelion.debugging.common.ApiResponse;
import hufslikelion.debugging.dto.request.StudentCreateRequest;
import hufslikelion.debugging.dto.response.StudentResponse;
import hufslikelion.debugging.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "학생", description = "학생 등록 API")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "학생 등록", description = "새 학생을 등록합니다.")
    @PostMapping
    public ApiResponse<StudentResponse> create(@Valid @RequestBody StudentCreateRequest request) {
        return ApiResponse.ok(studentService.create(request));
    }
}
