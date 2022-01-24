package alevel.model.impl;


import alevel.model.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "departments")
public class Department extends BaseUser {
    private String nameCompany;
    private String address;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "relations",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Employee> employees;

}
