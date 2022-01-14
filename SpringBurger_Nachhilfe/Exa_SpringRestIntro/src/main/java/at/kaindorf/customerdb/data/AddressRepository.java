package at.kaindorf.customerdb.data;

import at.kaindorf.customerdb.pojos.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}