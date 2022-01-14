package at.kaindorf.customerdb.data;

import at.kaindorf.customerdb.pojos.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}