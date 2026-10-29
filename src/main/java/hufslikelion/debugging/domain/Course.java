package hufslikelion.debugging.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    private Long id;

    private String name;

    private int capacity;

    private CourseDay dayOfWeek;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();

    protected Course() {
    }

    public Course(String name, int capacity, CourseDay dayOfWeek) {
        this.name = name;
        this.capacity = capacity;
        this.dayOfWeek = dayOfWeek;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public CourseDay getDayOfWeek() {
        return dayOfWeek;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}
