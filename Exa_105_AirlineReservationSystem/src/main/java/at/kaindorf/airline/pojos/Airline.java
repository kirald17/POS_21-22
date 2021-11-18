package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@IdClass(AirlineId.class)
public class Airline implements Serializable {
    //Primary Key
    @Id
    @Column(name = "airline_id")
    @NonNull
    private Long airlineId;
    @Id
    @Column(name = "airline_name")
    @NonNull
    private String airlineName;

    //OneToMany -> Bidirektional zu Aircraft
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "airline")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Aircraft> aircrafts = new ArrayList<>();

    //OneToMany -> Bidirektional zu Flight
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "airline", orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Flight> flights = new ArrayList<>();

}
