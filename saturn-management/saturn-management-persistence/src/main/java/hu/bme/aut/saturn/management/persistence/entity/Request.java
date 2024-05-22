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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id", nullable = false)
    private Manager assignee;

    private String description;

    private String comment;

    public enum RequestType {
        FAIRNESS,
        DISMISSAL,
        SUBJECT,
        OTHER
    }

    public enum Status {
        NEW,
        IN_PROGRESS,
        REQUESTER_FEEDBACK,
        IN_REVIEW,
        ACCEPTED,
        DENIED
    }
}
