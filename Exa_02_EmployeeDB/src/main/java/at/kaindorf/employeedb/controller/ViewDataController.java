package at.kaindorf.employeedb.controller;

import at.kaindorf.employeedb.database.DepartmentRepository;
import at.kaindorf.employeedb.database.EmployeeRepository;
import at.kaindorf.employeedb.pojo.Department;
import at.kaindorf.employeedb.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/")
@SessionAttributes("employees")
public class ViewDataController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> employees = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();

    @ModelAttribute
    public void addAttributes(Model model){
        employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
    }

    @GetMapping
    public String showDBData(Model model){
        return "viewData";
    }

    @PostMapping
    public String showFilteredEmployees(@ModelAttribute("department") String department, Model model){
        employees = employees.stream().filter(employee -> {
            if(employee.getDepartment() != null && employee.getDepartment().getDeptName().equals(department)){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        employees.stream().forEach(employee -> System.out.println(employee.getDepartment().getDeptName()));
        return "viewData";
    }
}
