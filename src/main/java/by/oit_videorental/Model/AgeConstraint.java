package by.oit_videorental.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "age_constraints")
@NoArgsConstructor
@AllArgsConstructor
public class AgeConstraint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "constraint_title")
    @Setter
    @Getter
    private String constraintTitle;

    @Column(name = "age")
    @Setter
    @Getter
    private int allowedAge;

    @OneToMany
    @Getter
    private Set<Category> categorySet;

    @OneToMany(mappedBy = "ageConstraint")
    @Getter
    private Set<Category> categories;

}
