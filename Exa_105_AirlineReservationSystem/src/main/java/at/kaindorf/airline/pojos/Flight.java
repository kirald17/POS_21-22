package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight implements Serializable {
    @Id
    @Column(name = "flight_id")
    private Long flightId;
    @Column(name = "departure_time")
    private LocalDate departureTime;
    @Column(name = "arrival_time")
    private LocalDate arrivalTime;
    //TODO: Ab hier wieder nur FKs
    //Airline zu Flight -> ManyToOne
    @ManyToOne
    @ToString.Exclude
    private Airline airline;
    //Aircraft zu Flight -> ManyToOne
    @ManyToOne
    @ToString.Exclude
    private Aircraft aircraft;
    //Airport zu Flight | Departure -> ManyToOne
    @ManyToOne
    @ToString.Exclude
    private Airport departureAirport;
    //Airport zu Flight | Arrival -> ManyToOne
    @ManyToOne
    @ToString.Exclude
    private Airport arrivalAirport;
}
