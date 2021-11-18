package at.kaindorf.schuldatenbank.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ClassTeacher.findByName", query = "SELECT ct FROM ClassTeacher ct WHERE ct.lastname = :name OR ct.firstname = :name"),
        @NamedQuery(name = "ClassTeacher.findByClassname", query = "SELECT ct FROM ClassTeacher ct WHERE ct.classname = :classname"),
        @NamedQuery(name = "ClassTeacher.findByGrade", query = "SELECT ct FROM ClassTeacher ct WHERE ct.classname.grade = :grade"),
        @NamedQuery(name = "ClassTeacher.countAll", query = "SELECT COUNT(ct) FROM ClassTeacher ct"),
})
public class ClassTeacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int teacherId;
    @NonNull
    private String initials;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String title;

    //Todo Beziehungen
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "classname_id")
    private Classname classname;
}
