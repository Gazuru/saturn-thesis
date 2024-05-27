package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.relation.CourseRegistration;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface CourseRegistrationRepository extends ListCrudRepository<CourseRegistration, UUID> {

    List<CourseRegistration> findAllByCourseIdInAndStudentUuid(List<UUID> courseId, UUID studentId);
}
