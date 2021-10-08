package at.kaindorf.intro.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Alle Daten, die ich nicht angebe, werden einfach ignoriert, obwohl sie vorhanden sind.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    //private int id;
    @JsonProperty("first_name")
    private String firstname;
    @JsonProperty("last_name")
    private String lastname;
    private String email;
    @JsonDeserialize(using = LocalDateDeserialize.class)
    private LocalDate dateOfBirth;

    @JsonIgnore
    private String company;
}
