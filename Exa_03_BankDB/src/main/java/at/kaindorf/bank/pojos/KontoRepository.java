package at.kaindorf.bank.pojos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KontoRepository extends JpaRepository<Konto, Integer> {
}