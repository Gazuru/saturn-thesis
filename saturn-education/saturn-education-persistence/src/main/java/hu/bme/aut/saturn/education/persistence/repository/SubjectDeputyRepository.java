package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.entity.Subject;
import hu.bme.aut.saturn.education.persistence.relation.SubjectDeputy;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface SubjectDeputyRepository extends ListCrudRepository<SubjectDeputy, UUID> {

    boolean existsByTeacherUuidAndSubjectId(UUID teacherUuid, UUID subjectId);

    @Query(value = "SELECT s.teacherUuid FROM SubjectDeputy s " +
            "WHERE s.subject = :subject")
    List<UUID> findTeacherUuidsBySubject(@Param("subject") Subject subject);
}
