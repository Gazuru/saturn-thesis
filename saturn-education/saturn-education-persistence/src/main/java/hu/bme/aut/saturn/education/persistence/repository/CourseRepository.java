package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.entity.Course;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface CourseRepository extends ListCrudRepository<Course, UUID> {

    List<Course> findAllBySubjectId(UUID subjectUuid);

    List<Course> findAllBySubjectIdIn(List<UUID> subjectIds);
}
