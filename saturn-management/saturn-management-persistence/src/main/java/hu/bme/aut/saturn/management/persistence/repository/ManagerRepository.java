package hu.bme.aut.saturn.management.persistence.repository;

import hu.bme.aut.saturn.management.persistence.entity.Manager;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface ManagerRepository extends ListCrudRepository<Manager, UUID> {
}
