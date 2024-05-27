package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.relation.CourseRegistration;
import hu.bme.aut.saturn.education.persistence.relation.CourseTeacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_COURSES")
public class Course extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private long length;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private String location;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CourseRegistration> courseRegistrations;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CourseTeacher> courseTeachers;


    public enum CourseType {
        LECTURE,
        PRACTICE,
        LABORATORY,
        EXAM;
    }

    public void addCourseTeacher(CourseTeacher courseTeacher) {
        if (courseTeachers == null) {
            courseTeachers = new ArrayList<>();
        }
        if (!courseTeachers.contains(courseTeacher)) {
            courseTeachers.add(courseTeacher);
        }
    }

    public void addCourseRegistration(CourseRegistration courseRegistration) {
        if (courseRegistrations == null) {
            courseRegistrations = new ArrayList<>();
        }
        if (!courseRegistrations.contains(courseRegistration)) {
            courseRegistrations.add(courseRegistration);
        }
    }

    public void removeCourseRegistration(CourseRegistration courseRegistration) {
        courseRegistrations.remove(courseRegistration);
    }
}
