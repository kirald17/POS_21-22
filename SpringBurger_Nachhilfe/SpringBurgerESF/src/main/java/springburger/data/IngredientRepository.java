package springburger.data;

import org.springframework.data.jpa.repository.JpaRepository;
import springburger.pojos.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}