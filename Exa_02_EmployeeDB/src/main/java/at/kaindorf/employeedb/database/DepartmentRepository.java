package at.kaindorf.employeedb.database;

import at.kaindorf.employeedb.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}