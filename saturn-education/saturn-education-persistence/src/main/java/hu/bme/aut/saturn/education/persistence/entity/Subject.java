package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.enums.PeriodOfYear;
import hu.bme.aut.saturn.education.persistence.relation.SubjectDeputy;
import hu.bme.aut.saturn.education.persistence.relation.SubjectRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_SUBJECTS")
public class Subject extends BaseEntity {

    @OneToMany(mappedBy = "subject")
    private List<Course> courses;

    @NotNull
    private Long creditIndex;

    @Enumerated(EnumType.STRING)
    private PeriodOfYear registerablePeriodOfYear;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<SubjectRegistration> subjectRegistrations;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<SubjectDeputy> subjectDeputies;

    @NotNull
    private Boolean archived = false;
}
