package at.kaindorf.springburger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/orders")
public class SpringOrderController {

    @GetMapping("/current")
    public String requestOrder(){
        return "orderForm";
    }

}
