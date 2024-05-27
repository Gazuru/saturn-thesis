package hu.bme.aut.saturn.education.persistence.relation;

import hu.bme.aut.saturn.education.persistence.entity.Exam;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_EXAM_REGISTRATIONS")
@IdClass(ExamRegistrationId.class)
public class ExamRegistration {

    @Id
    @NotNull
    private UUID studentUuid;

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EXAM_ID", nullable = false)
    private Exam exam;

    @Nullable
    private Long grade;


}
