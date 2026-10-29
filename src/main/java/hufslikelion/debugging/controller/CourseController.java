package hufslikelion.debugging.controller;

import hufslikelion.debugging.common.ApiResponse;
import hufslikelion.debugging.dto.request.CourseCreateRequest;
import hufslikelion.debugging.dto.response.CourseResponse;
import hufslikelion.debugging.service.CourseService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ApiResponse<List<CourseResponse>> findAll() {
        return ApiResponse.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<CourseResponse> find(@PathVariable Long id) {
        return ApiResponse.ok(courseService.find(id));
    }

    @PostMapping
    public ApiResponse<CourseResponse> create(@Valid @RequestBody CourseCreateRequest request) {
        return ApiResponse.ok(courseService.create(request));
    }
}
