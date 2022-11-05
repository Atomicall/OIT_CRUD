package by.oit_videorental.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "films")
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "title")
    @Setter
    @Getter
    private String filmTitle;

    @Column(name = "amount")
    @Setter
    @Getter
    private int amountOfFilms;

    @ManyToOne
    @JoinColumn(name = "publishing_company_id", referencedColumnName = "id")
    @Getter
    private PublishingComapny publishingComapny;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @Getter
    private Author author;

    @ManyToMany
    @JoinTable(name = "films_categories", joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    @Getter
    private Set<Category> filmCategories;

    @ManyToMany(mappedBy = "rentedFilms")
    @Getter
    private Set<Rent> rentSet;

    @ManyToMany(mappedBy = "filmsInOffice")
    @Getter
    private Set<Office> officeSet;


}
