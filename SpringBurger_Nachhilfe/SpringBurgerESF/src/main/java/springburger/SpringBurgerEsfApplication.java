package springburger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBurgerEsfApplication {

    public static void main(String[] args) {
        //Startet Tomcat und meine ganze Application
        SpringApplication.run(SpringBurgerEsfApplication.class, args);
    }

}
