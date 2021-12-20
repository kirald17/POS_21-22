package at.kaindorf.bank.pojos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kontoart")
public abstract class Konto {
    @Id
    @Column(name = "konto_id")
    private int id;

    @Column(nullable = false)
    private Long nummer;

    private BigDecimal kontostand;

    @ManyToMany(mappedBy = "konten")
    private List<Kunde> kunden;

}
