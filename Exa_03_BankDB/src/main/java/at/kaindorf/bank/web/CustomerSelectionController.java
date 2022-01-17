package at.kaindorf.bank.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/bank")
public class KontoController {

    @Autowired
    private KontoRepository kontoRepository;


    @GetMapping
    public String showInitialData(){

        return "bankView";
    }
}
