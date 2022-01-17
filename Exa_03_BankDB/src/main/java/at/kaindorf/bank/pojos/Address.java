package at.kaindorf.bank.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @Column(name = "address_id")
    private int id;
    @Column(length = 10, name = "street_number")
    private String streetNumber;
    private String city;
    @Column(length = 10, name = "zip_code")
    private String zipCode;
    @Column(name = "streetname")
    private String streetName;
}
