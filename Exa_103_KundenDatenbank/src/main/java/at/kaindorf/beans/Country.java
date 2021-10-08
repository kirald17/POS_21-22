package at.kaindorf.beans;

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
@NamedQueries({
        @NamedQuery(name = "Country.COUNT_ALL", query = "SELECT COUNT(c) FROM country c"),
        @NamedQuery(name = "Country.FIND_BY_NAME", query = "SELECT c.countryName FROM country c WHERE LOWER(c.countryName) = LOWER(:countryInfo) OR LOWER(c.countryCode) = LOWER(:countryInfo)"),
        @NamedQuery(name = "Country.FIND_ALL", query = "SELECT c.countryName FROM country c")
})
public class Country implements Serializable {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long countryId;
    @Column(name = "country_name")
    @NonNull
    private String countryName;
    @Column(name = "country_code")
    @NonNull
    private String countryCode;

    //Beziehungen
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Address> addressList = new ArrayList<>();

}
