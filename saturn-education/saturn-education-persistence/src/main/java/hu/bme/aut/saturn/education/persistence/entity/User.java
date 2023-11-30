package hu.bme.aut.saturn.education.persistence.entity;

import hu.bme.aut.saturn.education.persistence.enums.RoleType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class User extends BaseEntity{

    private RoleType roleType;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

}
