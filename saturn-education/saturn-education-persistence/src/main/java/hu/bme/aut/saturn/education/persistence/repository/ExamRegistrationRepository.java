package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.relation.ExamRegistration;
import hu.bme.aut.saturn.education.persistence.relation.ExamRegistrationId;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface ExamRegistrationRepository extends ListCrudRepository<ExamRegistration, ExamRegistrationId> {

    List<ExamRegistration> findAllByStudentUuid(UUID studentUuid);
}
