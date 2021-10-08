package at.kaindorf.beans;

import at.kaindorf.xml.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XMLDummy {
    private String country;
    private String country_code;
    private String postal_code;
    private String city;
    private String streetname;
    private String streetnumber;
    private String firstname;
    private String lastname;
    private String gender;
    private boolean active;
    private String email;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate since;

}
