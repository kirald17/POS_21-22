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
public class Adresse {
    @Id
    @Column(name = "adresse_id")
    private int id;
    @Column(length = 10)
    private String hausnummer;
    private String ort;
    @Column(length = 10)
    private String plz;
    private String strasse;
}
