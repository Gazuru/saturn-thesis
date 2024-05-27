package hu.bme.aut.saturn.education.persistence.repository;

import hu.bme.aut.saturn.education.persistence.entity.Course;
import hu.bme.aut.saturn.education.persistence.relation.CourseTeacher;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CourseTeacherRepository extends ListCrudRepository<CourseTeacher, UUID> {

    @Query(value = "SELECT c.teacherUuid FROM CourseTeacher c " +
            "WHERE c.course = :course")
    List<UUID> findTeacherUuidsByCourse(@Param("course") Course course);
}
