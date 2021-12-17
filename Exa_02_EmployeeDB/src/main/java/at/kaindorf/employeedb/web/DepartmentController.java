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

import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/administration")
@SessionAttributes({"department", "sortOrder"}) // Ganze Session gültig --> braucht aber ein @ModelAttribute
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @ModelAttribute("depts")
    public List<Department> populateDepartments(Model model){
        List<Department> departments = departmentRepository.findAll();
        // Klassenname:: --> kann das gleiche wie eine eigen geschrieben Lambda expression
        departments.sort(Comparator.comparing(Department::getDeptName));
        //Default --> Es wird das erste Department ausgewählt
        model.addAttribute("selectedDept", departments.get(0).getDeptName());
        model.addAttribute("department", departments.get(0));
        model.addAttribute("employees", departments.get(0).getEmployees());
        return departments;
    }

    @ModelAttribute("department")
    public Department populateDepartment(){
        return new Department();
    }

    @ModelAttribute("sortOrder")
    public Boolean populateSortOrder(){
        return Boolean.FALSE;
    }

    @GetMapping
    public String showDepartments(){
        return "departmentView";
    }

    @PostMapping
    public String showEmployees(Model model, @RequestParam("deptNo") String deptNo){
        Department department = departmentRepository.findDepartmentByDeptNo(deptNo);
        model.addAttribute("department", department);
        model.addAttribute("sortOrder", Boolean.TRUE);
        updateModel(model, department, null);
        return "departmentView";
    }

    @PostMapping("/sorted")
    public String sortEmployees(Model model, @SessionAttribute("department") Department department, @SessionAttribute("sortOrder") Boolean sortOrder){
        Comparator comparator = Comparator.comparing(Employee::getLastname).thenComparing(Employee::getFirstname);
        if(!sortOrder){
            comparator = comparator.reversed();
        }
        sortOrder = !sortOrder;
        model.addAttribute("sortOrder", sortOrder);
        updateModel(model, department, comparator);
        return "departmentView";
    }

    @PostMapping("/{id}")
    public String removeEmployee(Model model, @SessionAttribute("department") Department department, @PathVariable("id") Integer employeeNo){
        Employee employee = employeeRepository.findById(employeeNo).get(); // .get() greift auf das eigentliche Objekt zu
        department.getEmployees().remove(employee);
        employeeRepository.deleteById(employeeNo);
        departmentRepository.flush();
        updateModel(model, department, Comparator.comparing(Employee::getLastname).thenComparing(Employee::getFirstname));
        return "departmentView";
    }

    private void updateModel(Model model, Department department, Comparator comparator){
        department = departmentRepository.findDepartmentByDeptNo(department.getDeptNo());
        System.out.println(department.getDeptManager().toString());
        List<Employee> employees = department.getEmployees();
        //employees.add(department.getDeptManager());
        if(comparator != null){
            employees.sort(comparator);
        }
        model.addAttribute("employees", employees);
        model.addAttribute("selectedDept", department.getDeptName());
    }

}
