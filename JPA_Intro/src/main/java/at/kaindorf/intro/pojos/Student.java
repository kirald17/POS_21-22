package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "student")
//Wir importieren unseren eigenen PK
@IdClass(StudentPK.class)
public class Student implements Serializable {

    /*
        Fetch-Types
            LAZY: nur der PrimaryKey wird geladen, der Rest erst bei einer Abfrage
            EAGER: alle Daten werden direkt beim Start geladen
            Aufruf mit
            @Basic(fetch = FetchTyp.LAZY/EAGER)
     */

    /*
    // @Id generiert einen Private-Key
    @Id
    @Column(name = "student_id")
    @GeneratedValue
    private Long  studentId;
     */
    @Id
    @NonNull
    private String className;
    @Id
    @NonNull
    private Long catNo;
    @Column(nullable = false, length = 100)
    @NonNull
    private String firstname;
    @Column(nullable = false, length = 150)
    @NonNull
    private String lastname;
    @NonNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Transient //wird nicht in die Datenbank mitübernommen
    private String fullname;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true) //Wenn niemand diese Adresse mehr referenziert wird die Adresse gelösch
    @JoinColumn(name = "address")
    private Address address;

    public String getFullname(){
        return String.format("%s %s", lastname, firstname);
    }

    // Bidirektional um auch vom Student auf die Klasse zugreifen zu können
    @ManyToOne
    @JoinColumn(name = "schoolClass")
    private SchoolClass schoolClass;

    /*
        public Student() {
            studentId = UUID.randomUUID().toString();
        }
     */

    /*
    public Student(String firstname, String lastname, LocalDate dateOfBirth) {

            Wir erstellen unsere Objekte mit diesem Konstruktor d.h. dass unser Default Constructor nicht aufgerufen wird
            dadurch fehlt den Students die ID
            Mit this(); rufe ich den Parameterlosem Konstruktor auf und generiere mir die ID

        //this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }
    */
}
