package hufslikelion.debugging.dto.request;

import hufslikelion.debugging.domain.CourseDay;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreateRequest(
        @NotBlank String name,
        @Min(1) int capacity,
        @NotNull CourseDay dayOfWeek) {
}
