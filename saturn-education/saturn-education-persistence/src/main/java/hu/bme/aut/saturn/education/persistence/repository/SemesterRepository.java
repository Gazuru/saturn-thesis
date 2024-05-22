package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.entity.Semester;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface SemesterRepository extends ListCrudRepository<Semester, UUID> {

    @Query(value = "SELECT s " +
            "FROM Semester s " +
            "WHERE s.semesterStart <= CURRENT_DATE " +
            "AND s.semesterEnd >= CURRENT_DATE")
    Semester findCurrentSemester();

}
