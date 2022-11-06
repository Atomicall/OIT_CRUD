package by.oit_videorental.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "film_authors")
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "first_name")
    @Setter
    @Getter
    private String firstName;

    @Column(name = "last_name")
    @Setter
    @Getter
    private String lastName;

    @OneToMany
    @Getter
    private Set<Film> filmSet;

    @OneToMany(mappedBy = "author")
    @Getter
    private Set<Film> films;

}
