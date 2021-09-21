package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "school_class")

public class SchoolClass implements Serializable {
    @Id
    @Column(length = 10, name = "school_classname")
    @NonNull
    private String schoolClassName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "schoolClass")
    // Automatisches Ordern direkt in Java mögich
    @OrderBy("lastname desc, firstname asc ")
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student){
        if(studentList.contains(student)){
            //Wenn der Student eingefügt wird dann wird auh autmoatisch mit this hinzugefügt
            student.setSchoolClass(this);
            studentList.add(student);
        }
    }

}
