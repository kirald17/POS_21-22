package springburger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import springburger.data.OrderRepository;
import springburger.pojos.Burger;
import springburger.pojos.Order;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/current")
    public String showOrderForm(Model model, @SessionAttribute("designBurger") Burger designBurger){
        model.addAttribute("order", new Order());
        model.addAttribute("myBurger", designBurger);
        return "orderForm";
    }

    @PostMapping
    public String placeOrder(Order order){
        // Flush --> Direkt nach einem Objekt wird die Transaktionen abgeschlossen
        orderRepository.saveAndFlush(order);
        return "redirect:/design";
    }
}
