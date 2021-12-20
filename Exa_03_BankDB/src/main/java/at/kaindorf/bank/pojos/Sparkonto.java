package at.kaindorf.bank.pojos;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SPAR")
public class Sparkonto extends Konto{
    private BigDecimal guthabenzinssatz;
}
