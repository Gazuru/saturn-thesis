package hu.bme.aut.saturn.management.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_MAN_MANAGERS")
public class Manager extends BaseEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "assignee")
    private List<Request> requests;
}
