package at.kaindorf.customerdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long costumerId;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private Character gender;
    @NonNull
    private Boolean active;
    @NonNull
    private String email;
    @NonNull
    private LocalDate since;

    //Beziehungen
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    @ToString.Exclude
    @JsonIgnore
    private Address address;

}
