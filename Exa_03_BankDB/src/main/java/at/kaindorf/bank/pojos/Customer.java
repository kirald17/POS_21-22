package at.kaindorf.bank.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    @Id
    @Column(name = "customer_id")
    private Long id;

    private LocalDate birthdate;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
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
                @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"),

        },
            inverseJoinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
            }
    )
    private List<Account> accounts;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}

