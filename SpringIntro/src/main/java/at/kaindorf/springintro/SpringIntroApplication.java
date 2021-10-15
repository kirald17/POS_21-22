package at.kaindorf.springintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // Sagt das hier die Spring-Applikation startet
@RestController // Für die REST-API; Steht damit dem REST-Service zur verfügung

public class SpringIntroApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringIntroApplication.class, args);

    }

    @GetMapping("/hello") // Mit get-Request /hello wird diese Methode aufgerufen
    public String sayHello(@RequestParam(name = "name", defaultValue = "World") String name){
        return String.format("Hello %s", name);
    }

}
