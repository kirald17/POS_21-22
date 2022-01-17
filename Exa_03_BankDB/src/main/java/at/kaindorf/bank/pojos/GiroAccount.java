package at.kaindorf.bank.pojos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GIRO")
public class GiroAccount extends Account {
    private Double overdraft;

    private  Float debitInterest;

    private Float creditInterest;
}
