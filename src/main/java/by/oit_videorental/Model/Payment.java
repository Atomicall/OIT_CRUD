package by.oit_videorental.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Column(name = "payment_amount_byn")
    @Setter
    @Getter
    double paymentAmount;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;
    @Column(name = "payment_number", unique = true, nullable = false)
    @Setter
    @Getter
    private String paymentNumber;

    @Column(name = "payment_date")
    @Setter
    @Getter
    private Date paymentDate;

    @OneToOne
    @Getter
    private Rent rent; // ссылающаяся аренда

}
