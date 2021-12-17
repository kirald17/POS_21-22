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
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "employee-number is required")
    @Range(min = 1000, max = 500000, message = "number must be between 1000 and 5000")
    private Integer employeeNo;
    @Column(name = "first_name", length = 14, nullable = false)
    @NotBlank(message = "firstname is required")
    private String firstname;
    @Column(name = "last_name", length = 16, nullable = false)
    @NotBlank(message = "lastname is required")
    private String lastname;
    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "gender is required")
    private Gender gender;
    @Column(name = "birth_date", nullable = false)
    @JsonAlias("birthDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "birthdate is required")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    @ToString.Exclude
    private Department department;
}