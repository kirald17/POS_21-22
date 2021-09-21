package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Address implements Serializable {
    @Id
    @Column(name = "address_id", nullable = false)
    @GeneratedValue
    private Long addressId;
    @NonNull
    @Column(length = 100, nullable = false)
    private String city;
    @NonNull
    @Column(length = 100, nullable = false)
    private String street;
    @NonNull
    @Column(length = 100, nullable = false)
    private String number;


    @OneToOne(mappedBy = "address") // Wie es in dem anderen Table, auf der Javaseite heißt
    private Student student;
}
