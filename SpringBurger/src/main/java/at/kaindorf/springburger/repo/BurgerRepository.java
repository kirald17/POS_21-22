package at.kaindorf.springburger.repo;

import at.kaindorf.springburger.pojos.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Zwei Datentypen = Den der Klasse und den des PrivatKey
public interface BurgerRepository extends JpaRepository<Burger, Long> {

    @Query("select b from Burger b where b.name = :name order by b.id")
    List<Burger> findByNameOrderById(@Param("name") String name);

    @Query("select distinct b from Burger b where b.name = ?1")
    List<Burger> findDistinctByName();
}