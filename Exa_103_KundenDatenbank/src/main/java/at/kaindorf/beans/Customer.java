package at.kaindorf.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    private Long costumerId;
    private String firstname;
    private String lastname;
    private char gender;
    private boolean active;
    private String email;
    private LocalDate since;

    //Beziehungen
    @ManyToOne
    private Address address;

}
