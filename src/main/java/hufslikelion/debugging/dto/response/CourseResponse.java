package hufslikelion.debugging.dto.response;

import hufslikelion.debugging.domain.Course;
import hufslikelion.debugging.domain.CourseDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private Long id;
    private String name;
    private int capacity;
    private CourseDay dayOfWeek;

    public static CourseResponse from(Course course) {
        return new CourseResponse(course.getId(), course.getName(), course.getCapacity(), course.getDayOfWeek());
    }
}
