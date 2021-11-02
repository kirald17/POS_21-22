package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(AirlineId.class)
public class Airline implements Serializable {
    @Id
    @Column(name = "airline_id")
    private Long airlineId;
    @Id
    @Column(name = "airline_name")
    private String airlineName;

    //OneToMany -> Bidirektional zu Aircraft
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "aircraft")
    @ToString.Exclude
    private List<Aircraft> aircrafts;

    //OneToMany -> Bidirektional zu Flight
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "airline")
    @ToString.Exclude
    private List<Flight> flights;

}
