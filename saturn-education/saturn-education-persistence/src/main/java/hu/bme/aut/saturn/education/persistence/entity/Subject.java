package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.enums.PeriodOfYear;
import hu.bme.aut.saturn.education.persistence.relation.SubjectDeputy;
import hu.bme.aut.saturn.education.persistence.relation.SubjectRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_EDU_SUBJECTS")
public class Subject extends BaseEntity {

    @NotNull
    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Course> courses;

    @NotNull
    private Long creditIndex;

    @Enumerated(EnumType.STRING)
    private PeriodOfYear registerablePeriodOfYear;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubjectRegistration> subjectRegistrations;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubjectDeputy> subjectDeputies;

    @NotNull
    private Boolean archived = false;

    public void addSubjectDeputy(SubjectDeputy subjectDeputy) {
        if (subjectDeputies == null) {
            subjectDeputies = new ArrayList<>();
        }
        if (!subjectDeputies.contains(subjectDeputy)) {
            subjectDeputies.add(subjectDeputy);
        }
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void addSubjectRegistration(SubjectRegistration subjectRegistration) {
        if (subjectRegistrations == null) {
            subjectRegistrations = new ArrayList<>();
        }
        if (!subjectRegistrations.contains(subjectRegistration)) {
            subjectRegistrations.add(subjectRegistration);
        }
    }

    public void removeSubjectRegistration(SubjectRegistration subjectRegistration) {
        subjectRegistrations.remove(subjectRegistration);
    }
}
