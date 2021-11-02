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
public class Airport implements Serializable {
    @Id
    @Column(name = "airport_id")
    private Long airportId;
    private String country;
    private String city;
    private String name;

    //ManyToMany Verbindung (Bidirektional) zu Aircraft
    //Bei mappedBy, bezieht sich der Wert immer auf den Namen in der Javaklasse
    @ManyToMany(mappedBy = "aircraftId")
    @ToString.Exclude
    private List<Aircraft> aircrafts;

    //OneToMany Airport zu departure Flights
    @OneToMany(mappedBy = "departureAirport")
    @ToString.Exclude
    private List<Flight> departureFlights;

    //OneToMany Airport zu arrival Flights
    @OneToMany(mappedBy = "arrivalAirport")
    @ToString.Exclude
    private List<Flight> arrivalFlight;

}
