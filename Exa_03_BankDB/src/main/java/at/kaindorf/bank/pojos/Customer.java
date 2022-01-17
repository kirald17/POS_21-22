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
    @Column(name = "customer_id")
    private Long id;

    private LocalDate birthdate;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private int customerNumber;

    @Column(length = 100)
    private String lastname;

    @Column(length = 100)
    private String firstname;

    @ManyToMany
    @JoinTable(name = "customer_account",
        joinColumns = {
                @JoinColumn(name = "customer", referencedColumnName = "customer_id"),

        },
            inverseJoinColumns = {
                    @JoinColumn(name = "account", referencedColumnName = "account_id")
            }
    )
    private List<Konto> konten;

    @OneToOne
    @JoinColumn(name = "wohnort_id")
    private Adresse wohnort;
}

