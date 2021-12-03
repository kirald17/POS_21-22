package at.kaindorf.employeedb.database;

import at.kaindorf.employeedb.pojo.Department;
import at.kaindorf.employeedb.pojo.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component // InitDatabse wird von Spring in den IOC Container gepackt --> aber noch kein Zugriff
public class InitDatabase {

    @Autowired // Wie Component nur wird diese Variable auch injected
    private DepartmentRepository deptRepo;

    @Autowired
    private EmployeeRepository empRepo;

    @PostConstruct // Wird unmittelbar nach Aufruf nachdem Constructor ausgeführt
    public void initDatabaseFromJSON(){
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "employeedb.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Department> departments = new ArrayList<>();
        try {
            departments = mapper.readValue(path.toFile(), new TypeReference<List<Department>>() {});
            if(departments != null){
                for(Department d : departments){
                    // Zuerst alle Employees speichern
                    empRepo.saveAllAndFlush(d.getEmployees());
                    // Manager festlegen
                    empRepo.save(d.getDeptManager());
                    // Diese Version des Departments speichern
                    deptRepo.save(d);
                    for(Employee e : d.getEmployees()){
                        // Jetzt, nachdem ich die Departments habe, kann ich die Verbindung erstellen
                        // Einen Employee mit dem Department verbinden
                        e.setDepartment(d);
                    }
                    // Alle Employees noch einmal abspeichern
                    // Nur flush() sollte eigentlich wie ein .commit funktionieren?! Tuts aber nicht ?!
                    empRepo.saveAllAndFlush(d.getEmployees());
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }


    }

}
