package at.kaindorf.employeedb.pojo;

import at.kaindorf.employeedb.util.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employees")
public class Employee implements Serializable {
    @Id
    @Column(name = "emp_no")
    @JsonAlias("emp_no")
    private Integer employeeNo;
    @Column(name = "first_name", length = 14, nullable = false)
    private String firstname;
    @Column(name = "last_name", length = 16, nullable = false)
    private String lastname;
    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "birth_date", nullable = false)
    @JsonAlias("birthDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate DateOfBirth;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    @ToString.Exclude
    private Department department;
}