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
@NamedQueries({
        @NamedQuery(name = "Aircraft.GETALLAIRPORTSOFAIRPORT", query = "SELECT a FROM Aircraft a JOIN a.airports ar WHERE ar.name = (:airport)"),
        @NamedQuery(name = "Aircraft.GETALLAIRPORTSOFAIRLINE", query = "SELECT a FROM Aircraft a WHERE a.airline.airlineName = (:airlineName)")
})
public class Aircraft implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aircraft_id")
    private Long aircraftId;

    //Airline zu Aircraft -> ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @NonNull
    private Airline airline;

    //AircraftType zu Aircraft -> ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_type_id")
    @ToString.Exclude
    @NonNull
    private AircraftType aircraftType;

    //Aircraft zu Airport -> ManyToMany
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "aircraft_airport",
            joinColumns = {@JoinColumn(name = "aircraft_id")},
            inverseJoinColumns = {@JoinColumn(name = "airport_id")}
    )
    @ToString.Exclude
    private List<Airport> airports = new ArrayList<>();

    //OneToMany Aircraft zu Flight
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> flights = new ArrayList<>();

}
