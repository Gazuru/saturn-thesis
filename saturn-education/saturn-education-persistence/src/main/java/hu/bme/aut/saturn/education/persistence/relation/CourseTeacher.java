package hu.bme.aut.saturn.education.persistence.relation;

import hu.bme.aut.saturn.education.persistence.entity.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_COURSE_TEACHERS")
@IdClass(CourseTeacherId.class)
public class CourseTeacher {

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @Id
    @NotNull
    private UUID teacherUuid;
}
