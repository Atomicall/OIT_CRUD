package by.oit_videorental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client {

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

    @Column(name = "passport_number")
    @Setter
    @Getter
    private String passportNumber;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @Getter
    private Set<Rent> rentSet;

}
