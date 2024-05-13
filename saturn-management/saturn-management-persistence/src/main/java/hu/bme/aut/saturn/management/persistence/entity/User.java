package hu.bme.aut.saturn.management.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_MAN_USERS")
public class User extends BaseEntity {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String saturnCode;

    private LocalDate dateOfBirth;

    private String locationOfBirth;

    @OneToOne
    private Student studentInformation;

    @OneToOne
    private Teacher teacherInformation;

    @OneToOne
    private Manager managerInformation;

}
