package springburger.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springburger.pojos.Ingredient;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DBInit {
    @Autowired  //Objekt wird direkt erstellt von Spring --> Danke Spring
    IngredientRepository ingredientRepository;

    @PostConstruct
    public void initIngredients(){
        ingredientRepository.saveAll(Arrays.asList(
                new Ingredient("120B", "120g Ground Beef", Ingredient.Type.PATTY),
                new Ingredient("160B", "160g Ground Beef", Ingredient.Type.PATTY),
                new Ingredient("140B", "140g Turkey", Ingredient.Type.PATTY),
                new Ingredient("TOMA", "Tomato", Ingredient.Type.VEGGIE),
                new Ingredient("SALA", "Salad", Ingredient.Type.VEGGIE),
                new Ingredient("ONIO", "Onions", Ingredient.Type.VEGGIE),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("GOUD", "Gouda", Ingredient.Type.CHEESE)
        ));
    }

}
