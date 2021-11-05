package at.kaindorf.springburger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Von hier geht der ComponentScan aus
// ComponentScan regelt die Inversion of Control. Er schaut sich alle Klassen an und schaut, ob
// sie für IOC in Frage kommt (Ja bei Klassen mit @Controller etc)
public class SpringBurgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBurgerApplication.class, args);
    }

}
