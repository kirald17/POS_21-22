package at.kaindorf.springintro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/home") // Ich kann sowohl GET als POST requests machen
public class HomeController {

    //ResController liefert mir Daten zurück
    //Controller liefert mir den Namen einer Datei zurück
    @GetMapping("/home")
    public String home(){
        //Es wird nach einer Seite mit diesem Namen gesucht
        return "homepage";
    }


}
