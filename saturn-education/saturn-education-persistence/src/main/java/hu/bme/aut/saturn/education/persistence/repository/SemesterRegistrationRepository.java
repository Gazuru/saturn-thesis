package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.relation.SemesterRegistration;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface SemesterRegistrationRepository extends ListCrudRepository<SemesterRegistration, UUID> {

    SemesterRegistration findBySemesterIdAndStudentUuid(UUID semesterId, UUID studentUuid);

    List<SemesterRegistration> findAllByStudentUuid(UUID currentStudentUuid);
}

