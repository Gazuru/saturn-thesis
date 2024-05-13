package hu.bme.aut.saturn.management.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_MAN_STUDENTS")
public class Student extends BaseEntity {

    @OneToMany(mappedBy = "student")
    private List<Document> documents;

    private Degree degree;

    private enum Degree {
        COMPSCI_BSC,
        ELECTR_BSC,
        COMPSCI_MSC,
        ELECTR_MSC
    }
}
