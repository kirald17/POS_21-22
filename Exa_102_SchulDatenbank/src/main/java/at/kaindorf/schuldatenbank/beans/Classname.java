package at.kaindorf.schuldatenbank.beans;

import lombok.*;
import org.eclipse.persistence.platform.database.oracle.annotations.PLSQLTable;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Classname.findByName", query = "SELECT cn FROM Classname cn WHERE cn.name = :name"),
        @NamedQuery(name = "Classname.findAll", query = "SELECT cn FROM Classname cn"),
        @NamedQuery(name = "Classname.findByFloor", query = "SELECT cn FROM Classname cn WHERE cn.room.floor = :floor"),
        @NamedQuery(name = "Classname.countAll", query = "SELECT COUNT(cn) FROM Classname cn"),
})
public class Classname implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int classId;
    @NonNull
    private String name;
    @NonNull
    private int grade;
    @NonNull
    private int size;

    //TODO Beziehungen
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "class_teacher_id")
    private ClassTeacher classTeacher;
}
