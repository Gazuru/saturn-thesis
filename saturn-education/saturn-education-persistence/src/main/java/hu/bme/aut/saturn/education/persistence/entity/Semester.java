package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.enums.PeriodOfYear;
import hu.bme.aut.saturn.education.persistence.relation.SemesterRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_SEMESTERS")
public class Semester extends BaseEntity {

    @NotNull
    private LocalDate semesterStart;

    @NotNull
    private LocalDate semesterEnd;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PeriodOfYear periodOfYear;

    @OneToMany(mappedBy = "semester")
    private List<SemesterRegistration> semesterRegistrations;
}
