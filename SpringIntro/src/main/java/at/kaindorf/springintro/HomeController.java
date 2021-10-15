package at.kaindorf.springintro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //ResController liefert mir Daten zurück
    //Controller liefert mir den Namen einer Datei zurück
    @GetMapping("/home")
    public String home(){
        //Es wird nach einer Seite mit diesem Namen gesucht
        return "homepage";
    }
}
