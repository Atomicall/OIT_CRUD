package by.oit_videorental.model;

import by.oit_videorental.model.enumeration.FilmStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "film_copies")
@NoArgsConstructor
@AllArgsConstructor
public class FilmCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_number")
    @Getter
    @Setter
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Setter
    @Getter
    private FilmStatus filmStatus;

    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    @Getter
    private Office office;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    @Getter
    private Film filmData;


    @ManyToMany
    @Getter
    @JoinTable(name = "rents_films",
            joinColumns = @JoinColumn(name = "film_copy_id"),
            inverseJoinColumns = @JoinColumn(name = "rent_id"))
    private Set<Rent> rents; // в которых участвует фильм

}
