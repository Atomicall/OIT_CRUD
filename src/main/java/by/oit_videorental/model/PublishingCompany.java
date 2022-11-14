package by.oit_videorental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "publishing_companies",
        uniqueConstraints=
@UniqueConstraint(columnNames={"title"}))
@NoArgsConstructor
@AllArgsConstructor
public class PublishingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "title")
    @Setter
    @Getter
    private String companyTitle;

    @OneToMany(mappedBy = "publishingCompany", fetch = FetchType.EAGER)
    @Getter
    private Set<Film> films;

}
