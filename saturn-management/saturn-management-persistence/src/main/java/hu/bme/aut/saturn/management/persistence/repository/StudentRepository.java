package hu.bme.aut.saturn.management.persistence.repository;

import hu.bme.aut.saturn.management.persistence.entity.Student;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface StudentRepository extends ListCrudRepository<Student, UUID> {
}
