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
        @NamedQuery(name = "Room.findByClassName", query = "SELECT r FROM Room r WHERE r.name = :name"),
        @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
        @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor"),
        @NamedQuery(name = "Room.countAll", query = "SELECT COUNT(r) FROM Room r")
})
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int roomId;
    @NonNull
    private String name;
    @NonNull
    private Floor floor;

    public enum Floor{
        GROUND, FIRST
    }

    //TODO Beziehungen
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "classname_id")
    private Classname classname;
}
