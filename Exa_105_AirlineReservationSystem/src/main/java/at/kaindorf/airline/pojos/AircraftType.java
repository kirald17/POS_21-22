package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class AircraftType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aircraft_type_id")
    private Long aircraftTypeId;
    @Column(name = "type_name")
    @NonNull
    private String typeName;
    @Column(name = "seats")
    @NonNull
    private int seats;
}
