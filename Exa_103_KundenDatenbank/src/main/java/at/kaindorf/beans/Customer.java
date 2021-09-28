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
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @ManyToOne
    private Address address;

}
