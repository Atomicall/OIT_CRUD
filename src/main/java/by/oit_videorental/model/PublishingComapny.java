package by.oit_videorental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "publishing_companies")
@NoArgsConstructor
@AllArgsConstructor
public class PublishingComapny {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "title")
    @Setter
    @Getter
    private String companyTitle;

    @OneToMany
    @Getter
    private Set<Film> filmSet;

    @OneToMany(mappedBy = "publishingComapny")
    @Getter
    private Set<Film> films;

}
