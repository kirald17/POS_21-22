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
        @NamedQuery(name = "Airport.GETALLAIRPORTSOFAIRLINE", query = "SELECT a FROM Airport a JOIN a.aircrafts al WHERE al.airline.airlineName = (:airlineName)")
})
public class Airport implements Serializable {
    @Id
    @Column(name = "airport_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long airportId;
    @NonNull
    private String country;
    @NonNull
    private String city;
    @NonNull
    private String name;

    //ManyToMany Verbindung (Bidirektional) zu Aircraft
    //Bei mappedBy, bezieht sich der Wert immer auf den Namen in der Javaklasse
    @ManyToMany(mappedBy = "airports")
    @ToString.Exclude
    private List<Aircraft> aircrafts = new ArrayList<>();

    //OneToMany Airport zu departure Flights
    @OneToMany(mappedBy = "departureAirport", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> departureFlights = new ArrayList<>();

    //OneToMany Airport zu arrival Flights
    @OneToMany(mappedBy = "arrivalAirport", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> arrivalFlight = new ArrayList<>();

}
