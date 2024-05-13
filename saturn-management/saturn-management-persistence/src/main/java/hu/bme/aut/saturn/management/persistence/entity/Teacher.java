package hu.bme.aut.saturn.management.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_MAN_TEACHERS")
public class Teacher extends BaseEntity {

}
