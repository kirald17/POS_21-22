package at.kaindorf.schuldatenbank.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DummyTeacher implements Serializable {
    @XmlElement(name = "Kürzel")
    private String kuerzel;
    @XmlElement(name = "Titel")
    private String titel;
    @XmlElement(name = "Familienname")
    private String lastname;
    @XmlElement(name = "Vorname")
    private String firstname;
    @XmlElement(name = "Klasse")
    private String className;
    @XmlElement(name = "Schüler")
    private int anzSchüler;
    @XmlElement(name = "Raum")
    private String raum;
}
