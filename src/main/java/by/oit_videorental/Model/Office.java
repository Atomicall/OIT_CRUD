package by.oit_videorental.Model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "adress_city")
    @Setter
    @Getter
    private String addressCity;

    @Column(name = "adress")
    @Setter
    @Getter
    private int address;

    @Column(name = "office_capacity")
    @Setter
    @Getter
    private int officeCapacity;

    @ManyToMany
    @JoinTable(name = "offices_films", joinColumns = {@JoinColumn(name = "office_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")})
    @Getter
    private Set<Film> filmsInOffice;

    @OneToMany
    @Getter
    private Set<Rent> rentSet;

}