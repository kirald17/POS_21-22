package at.kaindorf.customerdb.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "country")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Country implements Serializable {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long countryId;
    @Column(name = "country_name")
    @NonNull
    private String countryName;
    @Column(name = "country_code")
    @NonNull
    private String countryCode;

    //Beziehungen
    @OneToMany(mappedBy = "country")
    @NonNull
    private List<Address> addressList = new ArrayList<>();

}
