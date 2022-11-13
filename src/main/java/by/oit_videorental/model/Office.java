package by.oit_videorental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "offices")
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(name = "address_city")
    @Setter
    @Getter
    private String addressCity;

    @Column(name = "address")
    @Setter
    @Getter
    private int address;

    @OneToMany
    @JoinTable(name = "film_copies", joinColumns = {@JoinColumn(name = "office_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")})
    @Getter
    private Set<FilmCopy> filmsInOffice;

    @OneToMany
    @Getter
    private Set<Rent> rentSet;

}