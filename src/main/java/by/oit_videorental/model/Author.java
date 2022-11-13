package by.oit_videorental.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        name="film_authors",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"first_name", "last_name"})
)
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "first_name")
    @Setter
    @Getter
    private String firstName;

    @Column(name = "last_name")
    @Setter
    @Getter
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    @Getter
    private Set<Film> filmSet;

}
