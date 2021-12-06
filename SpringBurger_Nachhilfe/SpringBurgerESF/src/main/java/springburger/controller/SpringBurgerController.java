package springburger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springburger.data.IngredientRepository;
import springburger.pojos.Burger;
import springburger.pojos.Ingredient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller // Vom Frontend kann ich auf diesen Controller zugreifen
@Slf4j // Logging informationen
@RequestMapping("/design") // Endpoint definiert
@SessionAttributes("designBurger")
public class SpringBurgerController {

    @Autowired
    private  IngredientRepository ingredientRepository;
    private List<Ingredient> ingredients;

    //ERstellt meine Variable egal wo und wie
    @ModelAttribute(name = "designBurger")
    public Burger burger(){
        return new Burger();
    }

    @ModelAttribute()
    public void addAttribut(Model model){
        ingredients = ingredientRepository.findAll();
        Map<String, List<Ingredient>> ingredientMap = new HashMap<>();

        for(Ingredient.Type t : Ingredient.Type.values()){
            ingredientMap.put(t.toString(), filterByType(t));
        }
        model.addAttribute("ingredients", ingredientMap);

    }


    private List<Ingredient> filterByType(Ingredient.Type type){
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
    }

    // Aufgerufen wenn wir in der Applikation /design ansurfen
    @GetMapping
    public String showDesignForm(Model model){
        return "designForm";
    }

    @PostMapping
    public String processBurger(@ModelAttribute("designBurger") Burger designBurger, String[] ingredients){
        return "redirect:/orders/current";
    }

}
