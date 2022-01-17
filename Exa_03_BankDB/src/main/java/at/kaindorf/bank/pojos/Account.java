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
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {
    @Id
    @Column(name = "account_id")
    private int id;

    @Column(nullable = false, name = "account_number")
    private Long accountNumber;

    @Column(name = "balance")
    private Double balance;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customerList;

}
