package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Flight.GETALLFLIGHTSOFAIRLINE", query = "SELECT f FROM Flight f  WHERE f.airline.airlineName = (:airlineName)"),
        @NamedQuery(name = "Flight.GETALLARRIVALFLIGHTSOFAIRPORT", query = "SELECT f FROM Flight f  WHERE f.arrivalAirport.airportId = (:id)"),
        @NamedQuery(name = "Flight.GETALLDEPARTUREFLIGHTSOFAIRPORT", query = "SELECT f FROM Flight f  WHERE f.departureAirport.name = (:name)")
})
public class Flight implements Serializable {
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long flightId;
    @Column(name = "departure_time")
    @NonNull
    private LocalTime departureTime;
    @Column(name = "arrival_time")
    @NonNull
    private LocalTime arrivalTime;

    //Airline zu Flight -> ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @NonNull
    private Airline airline;

    //Aircraft zu Flight -> ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id")
    @ToString.Exclude
    @NonNull
    private Aircraft aircraft;

    //Airport zu Flight | Departure -> ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport")
    @ToString.Exclude
    @NonNull
    private Airport departureAirport;

    //Airport zu Flight | Arrival -> ManyToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport")
    @ToString.Exclude
    @NonNull
    private Airport arrivalAirport;
}
