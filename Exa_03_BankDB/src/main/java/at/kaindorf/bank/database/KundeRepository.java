package at.kaindorf.bank.database;

import at.kaindorf.bank.pojos.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundeRepository extends JpaRepository<Kunde, Long> {

    Kunde findByNachnameIsLike(String nachname);

}