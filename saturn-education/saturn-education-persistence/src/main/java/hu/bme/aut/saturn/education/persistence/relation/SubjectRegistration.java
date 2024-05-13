package hu.bme.aut.saturn.education.persistence.relation;

import hu.bme.aut.saturn.education.persistence.entity.Subject;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_SUBJECT_REGISTRATIONS")
@IdClass(SubjectRegistrationId.class)
public class SubjectRegistration {

    @Id
    @NotNull
    private UUID studentUuid;

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @ManyToOne
    private SemesterRegistration semesterRegistration;

    @Nullable
    private Long grade;
}
