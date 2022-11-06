package by.oit_videorental.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "price")
    @Setter
    @Getter
    private BigDecimal filmPrice;

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

    @OneToMany(mappedBy = "filmData")
    @Getter
    private Set<FilmCopy> filmCopies;


}
