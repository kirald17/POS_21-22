package at.kaindorf.employeedb.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "departments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department implements Serializable {
    @Id
    @Column(name = "dept_no", length = 4)
    @JsonAlias("number")
    private String deptNo;
    @Column(name = "dept_name", length = 40, nullable = false)
    @JsonAlias("name")
    private String deptName;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Employee> employees = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "emp_no")
    @ToString.Exclude
    private Employee deptManager;

    // Einen Employee hinzufügen
    public void addEmployee(Employee employee) {
        if(!employees.contains(employee)){
            employees.add(employee);
        }
    }
}