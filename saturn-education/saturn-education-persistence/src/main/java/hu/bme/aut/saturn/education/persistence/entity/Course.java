package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.relation.CourseRegistration;
import hu.bme.aut.saturn.education.persistence.relation.CourseTeacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseRegistration> courseRegistrations;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseTeacher> courseTeachers;

    private enum CourseType {
        LECTURE,
        PRACTICE,
        LABORATORY,
        EXAM
    }
}
