package alevel.model.impl;


import alevel.model.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "employees")
public class Employee extends BaseUser {


    private String firstname;
    private String lastname;
    private String username;
    @JsonIgnore
    @ManyToMany(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employees"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Department> departments;

}
