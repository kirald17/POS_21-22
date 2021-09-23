package at.kaindorf.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address implements Serializable {
    @Id
    @Column(name = "address_id")
    private Long addresId;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "street_number")
    private int streetNumber;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;

    //Beziehungen
    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "address")
    private List<Customer> customerList = new ArrayList<>();

}
