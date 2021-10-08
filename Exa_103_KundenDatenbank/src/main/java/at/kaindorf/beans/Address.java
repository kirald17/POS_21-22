package at.kaindorf.beans;

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
@NamedQueries({
        @NamedQuery(name = "Address.COUNT_ALL", query = "SELECT COUNT(a) FROM address a")
})
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
    @ManyToOne(cascade = CascadeType.ALL)
    @NonNull
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Customer> customerList = new ArrayList<>();

}
