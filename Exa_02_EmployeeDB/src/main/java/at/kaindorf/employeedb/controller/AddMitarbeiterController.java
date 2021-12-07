package at.kaindorf.employeedb.controller;

import at.kaindorf.employeedb.database.DepartmentRepository;
import at.kaindorf.employeedb.database.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/addEmployee")
public class AddMitarbeiterController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String addEmployee(){
        return "addMitarbeiter";
    }

}
