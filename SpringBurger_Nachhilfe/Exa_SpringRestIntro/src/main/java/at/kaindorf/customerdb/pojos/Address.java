package at.kaindorf.customerdb.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class Address implements Serializable {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long addresId;
    @Column(name = "street_name")
    @NonNull
    private String streetName;
    @Column(name = "street_number")
    @NonNull
    private int streetNumber;
    @Column(name = "postal_code")
    @NonNull
    private String postalCode;
    @Column(name = "city")
    @NonNull
    private String city;

    //Beziehungen
    @ManyToOne
    @NonNull
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "address")
    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Customer> customerList = new ArrayList<>();

}
