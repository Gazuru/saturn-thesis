package hu.bme.aut.saturn.education.persistence.relation;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class SemesterRegistrationId implements Serializable {

    @NotNull
    private UUID studentUuid;

    @NotNull
    private UUID semester;
}
