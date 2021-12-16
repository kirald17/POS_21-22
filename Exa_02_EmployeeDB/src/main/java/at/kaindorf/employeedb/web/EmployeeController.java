package at.kaindorf.employeedb.web;

import at.kaindorf.employeedb.database.DepartmentRepository;
import at.kaindorf.employeedb.database.EmployeeRepository;
import at.kaindorf.employeedb.pojo.Department;
import at.kaindorf.employeedb.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @ModelAttribute("employee")
    public Employee populateEmployee(){
        return new Employee();
    }


    @GetMapping
    public String showEmployeeForm(){
        return "employeeView";
    }

    @PostMapping
    public String addEmployee(@ModelAttribute("employee") Employee employee, @SessionAttribute("department") Department department){
        log.info("new employee" + employee);
        log.info("department" + department);
        employee.setDateOfBirth(LocalDate.now());
        department.addEmployee(employee);
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return "forward:/administration";
        //return "forward:/administration?deptNo=" + department.getDeptNo();
    }

}
