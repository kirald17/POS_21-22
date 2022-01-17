package at.kaindorf.bank.pojos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("GIRO")
public class Girokonto extends Konto{
    private BigDecimal kreditlimit;

    private  BigDecimal sollzinssatz;

    private BigDecimal ueberziehungszinssatz;
}
