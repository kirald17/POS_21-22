package at.kaindorf.bank.database;

import at.kaindorf.bank.pojos.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {
}