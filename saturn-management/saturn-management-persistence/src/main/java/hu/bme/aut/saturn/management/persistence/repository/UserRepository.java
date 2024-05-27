package hu.bme.aut.saturn.management.persistence.repository;

import hu.bme.aut.saturn.management.persistence.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends ListCrudRepository<User, UUID> {

    User findUserBySaturnCode(String saturnCode);

    List<User> findUsersByTeacherInformationNotNull();

    List<User> findUserByTeacherInformationIdIn(List<UUID> teacherInformationId);

    List<User> findUsersByStudentInformationIdIn(List<UUID> studentUuids);

    @Query(value = "SELECT m.firstName || ' ' || m.lastName FROM User m " +
            "WHERE m.managerInformation.id = :managerUuid")
    String getNameOfManager(@Param("managerUuid") UUID managerUuid);

    @Query(value = "SELECT m.firstName || ' ' || m.lastName FROM User m " +
            "WHERE m.studentInformation.id = :studentUuid")
    String getNameOfStudent(@Param("studentUuid") UUID studentUuid);
}
