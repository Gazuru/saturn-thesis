package hu.bme.aut.saturn.education.persistence.relation;

import hu.bme.aut.saturn.education.persistence.entity.Semester;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_SEMESTER_REGISTRATIONS")
@IdClass(SemesterRegistrationId.class)
public class SemesterRegistration {

    @Id
    @NotNull
    private UUID studentUuid;

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SEMESTER_ID", nullable = false)
    private Semester semester;

    @OneToMany(mappedBy = "semesterRegistration", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SubjectRegistration> subjectRegistrations;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    public enum Status {
        INACTIVE,
        PASSIVE,
        ACTIVE,
        FINISHED
    }
}
