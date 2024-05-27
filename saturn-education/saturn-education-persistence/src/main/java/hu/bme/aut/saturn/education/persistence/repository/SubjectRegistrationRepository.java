package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.entity.Semester;
import hu.bme.aut.saturn.education.persistence.relation.SemesterRegistration;
import hu.bme.aut.saturn.education.persistence.relation.SubjectRegistration;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface SubjectRegistrationRepository extends ListCrudRepository<SubjectRegistration, UUID> {

    List<SubjectRegistration> findAllBySemesterRegistrationSemesterAndStudentUuid(Semester semester, UUID studentUuid);

    List<SubjectRegistration> findAllBySemesterRegistration(SemesterRegistration semesterRegistration);

    List<SubjectRegistration> findAllBySubjectId(UUID subjectId);

    SubjectRegistration findBySubjectIdAndSemesterRegistrationAndStudentUuid(UUID subjectUuid, SemesterRegistration semesterRegistration, UUID studentUuid);
}
