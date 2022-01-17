package at.kaindorf.bank.database;

import at.kaindorf.bank.pojos.GiroAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiroAccountRepository extends JpaRepository<GiroAccount, Integer> {
}