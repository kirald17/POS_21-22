package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AircraftType implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "aircraft_type_id")
    private Long aircraftTypeId;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "seats")
    private Long seats;
}
