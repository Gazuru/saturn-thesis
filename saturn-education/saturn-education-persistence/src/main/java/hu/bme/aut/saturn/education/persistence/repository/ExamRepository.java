package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.entity.Exam;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface ExamRepository extends ListCrudRepository<Exam, UUID> {

    List<Exam> findAllBySubjectId(UUID subjectUuid);
}
