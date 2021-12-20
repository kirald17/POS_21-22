package at.kaindorf.bank.database;

import at.kaindorf.bank.pojos.Konto;
import at.kaindorf.bank.pojos.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KontoRepository extends JpaRepository<Konto, Integer> {

    List<Konto> getByCustomerName(String nachname);

}