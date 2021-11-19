package at.kaindorf.springburger.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Burger implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 5, message = "must at least be 5 letters")
    private String name;
    @NotNull
    @Size(min = 2, message = "choose at least 2 ingredients")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    private Order order;

}

