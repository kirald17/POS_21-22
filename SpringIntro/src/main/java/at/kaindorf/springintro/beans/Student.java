package at.kaindorf.springintro.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Value //Klasse wird mutable
public class Student implements Serializable {
    String firstname;
    String lastname;
    int age;
}
