package hu.bme.aut.saturn.management.persistence.repository;

import hu.bme.aut.saturn.management.persistence.entity.Teacher;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface TeacherRepository extends ListCrudRepository<Teacher, UUID> {
}
