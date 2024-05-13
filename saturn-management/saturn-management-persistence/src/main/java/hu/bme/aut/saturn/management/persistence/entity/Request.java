package hu.bme.aut.saturn.management.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_MAN_REQUESTS")
public class Request extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private UUID requesterUuid;

    @ManyToOne
    private Manager assignee;

    private String description;

    private String comment;

    private enum RequestType {
        FAIRNESS,
        DISMISSAL,
        SUBJECT,
        OTHER
    }

    private enum Status {
        NEW,
        IN_PROGRESS,
        REQUESTER_FEEDBACK,
        IN_REVIEW,
        ACCEPTED,
        DENIED
    }
}
