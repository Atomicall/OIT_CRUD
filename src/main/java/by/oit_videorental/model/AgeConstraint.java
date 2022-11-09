package by.oit_videorental.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@Table(name = "age_constraints")
@NoArgsConstructor
@AllArgsConstructor
public class AgeConstraint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
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


}