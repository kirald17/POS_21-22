package at.kaindorf.employeedb.database;

import at.kaindorf.employeedb.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}