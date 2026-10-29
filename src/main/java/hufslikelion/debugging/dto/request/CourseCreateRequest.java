package hufslikelion.debugging.dto.request;

import hufslikelion.debugging.domain.CourseDay;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {

    @NotBlank
    private String name;

    @Min(1)
    private int capacity;

    @NotNull
    private CourseDay dayOfWeek;
}
