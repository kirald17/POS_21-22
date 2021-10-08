package at.kaindorf.beans;

import at.kaindorf.json.JSONDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@JsonDeserialize(using = JSONDeserializer.class)
@NamedQueries({
        @NamedQuery(name = "Customer.COUNT_ALL", query = "SELECT COUNT(c) FROM customer c"),
        @NamedQuery(name = "Customer.FIND_YEARS", query = "SELECT DISTINCT EXTRACT(YEAR FROM c.since) FROM customer c ORDER BY EXTRACT(YEAR FROM c.since) ASC"),
        @NamedQuery(name = "Customer.FIND_FROM_COUNTRY", query = "SELECT c FROM customer c WHERE LOWER(c.address.country.countryName) = LOWER(:country)")
})
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long costumerId;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private char gender;
    @NonNull
    private boolean active;
    @NonNull
    private String email;
    @NonNull
    private LocalDate since;

    //Beziehungen
    @ManyToOne(cascade = CascadeType.ALL)
    @NonNull
    @ToString.Exclude
    private Address address;

}
