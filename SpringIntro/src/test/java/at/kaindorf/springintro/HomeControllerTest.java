package at.kaindorf.springintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired //Injection durch Spring Framework
    MockMvc mockMvc;

    @Test
    public void testHome() throws Exception{
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk()) // HTTP status 200
                .andExpect(view().name("homepage")) // Viewname abfragen
                .andExpect(content().string(containsString("Welcome to my homepage"))) // Den Content der Website
                .andDo(print());
    }

}