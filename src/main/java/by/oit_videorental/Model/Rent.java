package by.oit_videorental.Model;

import by.oit_videorental.Model.Enum.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "rents")
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "start_date")
    @Setter
    @Getter
    private Date rentStartingDate;

    @Column(name = "duration")
    @Setter
    @Getter
    private int rentDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "rent_status")
    @Setter
    @Getter
    private RentStatus rentStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @Getter
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    @Getter
    private Office office;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @Getter
    private Client client;

    @ManyToMany
    @JoinTable(name = "rents_films", joinColumns = {@JoinColumn(name = "rent_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")})
    @Getter
    private Set<Film> rentedFilms;
}
