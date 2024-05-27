package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.relation.ExamRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_EXAMS")
public class Exam extends BaseEntity {

    @NotNull
    private LocalDate examDate;

    @NotNull
    private LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExamRegistration> examRegistrations;

    private String location;

    public void addExamRegistration(ExamRegistration examRegistration) {
        if (examRegistrations == null) {
            examRegistrations = new ArrayList<>();
        }
        if (!examRegistrations.contains(examRegistration)) {
            examRegistrations.add(examRegistration);
        }
    }

}
