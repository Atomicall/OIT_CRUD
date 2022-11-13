package by.oit_videorental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(name = "amount_byn")
    @Setter
    @Getter
    private BigDecimal paymentAmount;

    @Column(name = "number", unique = true, nullable = false)
    @Setter
    @Getter
    private String paymentNumber;

    @Column(name = "date")
    @Setter
    @Getter
    private Date paymentDate;

    @OneToOne(mappedBy = "payment")
    @Getter
    private Rent rent; // ссылающаяся аренда
}
