package at.kaindorf.bank.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "kunde")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kunde {
    @Id
    @Column(name = "kunde_id")
    private Long id;

    private LocalDate geburtsdatum;

    @Column(nullable = false)
    private char geschlecht;

    @Column(nullable = false)
    private int kundennummer;

    @Column(length = 100)
    private String nachname;

    @Column(length = 100)
    private String vorname;

    @ManyToMany
    @JoinTable(name = "kunde_konto",
        joinColumns = {
                @JoinColumn(name = "kunde", referencedColumnName = "kunde_id"),

        },
            inverseJoinColumns = {
                    @JoinColumn(name = "konto", referencedColumnName = "konto_id")
            }
    )
    private List<Konto> konten;

    @OneToOne
    @JoinColumn(name = "wohnort_id")
    private Adresse wohnort;
}

