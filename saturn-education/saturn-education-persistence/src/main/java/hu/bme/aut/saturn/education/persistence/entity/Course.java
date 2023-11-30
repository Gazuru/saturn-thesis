package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.enums.CourseType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends BaseEntity {

    private String name;

    private CourseType courseType;

}
