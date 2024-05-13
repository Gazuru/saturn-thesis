package hu.bme.aut.saturn.management.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_MAN_DOCUMENTS")
public class Document extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @NotNull
    private String identifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private enum DocumentType {
        ID_CARD,
        STUDENT_ID_CARD,
        TAX_ID_CARD
    }
}
