package at.kaindorf.bank.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kontoart")
@NamedQueries({
        @NamedQuery(name = "Konto.getByCustomerName", query = "SELECT k FROM Konto k JOIN k.kunden ku WHERE ku.nachname LIKE :nachname")
})
public abstract class Konto {
    @Id
    @Column(name = "konto_id")
    private int id;

    @Column(nullable = false)
    private Long nummer;

    private BigDecimal kontostand;

    @ManyToMany(mappedBy = "konten")
    private List<Customer> kunden;

}
