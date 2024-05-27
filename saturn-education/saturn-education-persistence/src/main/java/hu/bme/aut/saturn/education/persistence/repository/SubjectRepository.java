package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.entity.Subject;
import hu.bme.aut.saturn.education.persistence.enums.PeriodOfYear;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends ListCrudRepository<Subject, UUID> {

    @Query(value = "SELECT s FROM Subject s " +
            "JOIN s.subjectDeputies sD " +
            "WHERE sD.teacherUuid = :teacherUuid")
    List<Subject> findAllByTeacherUuid(@Param("teacherUuid") UUID teacherUuid);

    List<Subject> findSubjectsByRegisterablePeriodOfYear(PeriodOfYear periodOfYear);
}
