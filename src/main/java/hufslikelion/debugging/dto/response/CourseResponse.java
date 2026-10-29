package hufslikelion.debugging.dto.response;

import hufslikelion.debugging.domain.Course;
import hufslikelion.debugging.domain.CourseDay;

public record CourseResponse(Long id, String name, int capacity, CourseDay dayOfWeek) {

    public static CourseResponse from(Course course) {
        return new CourseResponse(course.getId(), course.getName(), course.getCapacity(), course.getDayOfWeek());
    }
}
