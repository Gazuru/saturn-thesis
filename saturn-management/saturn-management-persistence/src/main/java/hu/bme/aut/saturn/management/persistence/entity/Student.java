package hu.bme.aut.saturn.management.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_MAN_STUDENTS")
public class Student extends BaseEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL)
    private List<Document> documents;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Degree degree;

    public enum Degree {
        COMPSCI_BSC,
        ELECTR_BSC,
        COMPSCI_MSC,
        ELECTR_MSC
    }
}
