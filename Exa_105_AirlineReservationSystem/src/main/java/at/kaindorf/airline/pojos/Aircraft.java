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
public class Aircraft implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "aircraft_id")
    private Long aircraftId;

    //TODO: Beziehungen -> Ab hier nur FKs
    //Airline zu Aircraft -> ManyToOne
    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Airline airline;
    //AircraftType zu Aircraft -> ManyToOne
    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private AircraftType aircraftType;
    //Aircraft zu Airport -> ManyToMany
    @ManyToMany(mappedBy = "airportId")
    @ToString.Exclude
    private List<Airport> airports;

    //OneToMany Aircraft zu Flight
    @OneToMany(mappedBy = "aircraft")
    @ToString.Exclude
    private List<Flight> flights;

}
