package hu.bme.aut.saturn.management.persistence.repository;

import hu.bme.aut.saturn.management.persistence.entity.Request;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface RequestRepository extends ListCrudRepository<Request, UUID> {

    List<Request> findAllByRequesterUuid(UUID studentUuid);

    @Query(value = "SELECT r FROM Request r " +
            "WHERE r.assignee.id = :managerUuid " +
            "AND r.status NOT IN (ACCEPTED, DENIED) ")
    List<Request> findAllByAssigneeIdAndNotCompleted(@Param("managerUuid") UUID managerUuid);
}
