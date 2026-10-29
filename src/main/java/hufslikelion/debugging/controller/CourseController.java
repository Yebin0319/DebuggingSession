package hufslikelion.debugging.controller;

import hufslikelion.debugging.common.ApiResponse;
import hufslikelion.debugging.dto.request.CourseCreateRequest;
import hufslikelion.debugging.dto.response.CourseResponse;
import hufslikelion.debugging.service.CourseService;
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

@Tag(name = "강의", description = "강의 목록 조회, 단건 조회, 등록 API")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "강의 목록 조회", description = "등록된 모든 강의를 조회합니다.")
    @GetMapping
    public ApiResponse<List<CourseResponse>> findAll() {
        return ApiResponse.ok(courseService.findAll());
    }

    @Operation(summary = "강의 단건 조회", description = "강의 id로 강의 한 건을 조회합니다. 없는 id면 404를 반환합니다.")
    @GetMapping("/{id}")
    public ApiResponse<CourseResponse> find(
            @Parameter(description = "강의 id", example = "1") @PathVariable Long id) {
        return ApiResponse.ok(courseService.find(id));
    }

    @Operation(summary = "강의 등록", description = "새 강의를 등록합니다. 요일은 MON, TUE, WED, THU, FRI 중 하나입니다.")
    @PostMapping
    public ApiResponse<CourseResponse> create(@Valid @RequestBody CourseCreateRequest request) {
        return ApiResponse.ok(courseService.create(request));
    }
}
