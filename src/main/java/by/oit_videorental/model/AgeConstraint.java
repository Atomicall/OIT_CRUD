package by.oit_videorental.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@Table(name = "age_constraints",
        uniqueConstraints=
@UniqueConstraint(columnNames={"constraint_title"}))
@NoArgsConstructor
@AllArgsConstructor
public class AgeConstraint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    // ?
    @Setter
    private long id;

    @Column(name = "constraint_title")
    @Setter
    @Getter
    private String constraintTitle;

    @Column(name = "age")
    @Setter
    @Getter
    private int allowedAge;

   @JsonManagedReference
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
    @OneToMany(mappedBy = "ageConstraint", fetch = FetchType.EAGER)
    @Getter
    private Set<Category> categories = new LinkedHashSet<>();


    @Override
    public String toString() {
        return "AgeConstraint{" +
                "id=" + id +
                ", constraintTitle='" + constraintTitle + '\'' +
                ", allowedAge=" + allowedAge +
                "}\n";
    }
}
