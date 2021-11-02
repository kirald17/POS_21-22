package at.kaindorf.airline.pojos;

import lombok.Data;

import java.io.Serializable;

@Data
public class AirlineId implements Serializable {
    private Long airlineId;
    private String airlineName;
}
