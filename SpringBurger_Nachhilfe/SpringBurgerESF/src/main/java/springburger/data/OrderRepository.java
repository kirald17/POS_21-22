package springburger.data;

import org.springframework.data.jpa.repository.JpaRepository;
import springburger.pojos.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}