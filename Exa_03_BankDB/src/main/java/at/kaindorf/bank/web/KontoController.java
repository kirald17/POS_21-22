package at.kaindorf.bank.web;


import at.kaindorf.bank.database.KontoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/bank")
public class KontoController {

    @Autowired
    private KontoRepository kontoRepository;

    @ModelAttribute
    public void setDummyData(Model model){
        model.addAttribute("konten", kontoRepository.getByCustomerName("%Sch%"));
    }

    @GetMapping
    public String showInitialData(){

        return "bankView";
    }
}
