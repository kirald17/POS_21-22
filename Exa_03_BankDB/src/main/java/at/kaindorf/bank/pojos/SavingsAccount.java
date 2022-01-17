package at.kaindorf.bank.pojos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SPAR")
public class SavingsAccount extends Account {
    private Double interest;
}
