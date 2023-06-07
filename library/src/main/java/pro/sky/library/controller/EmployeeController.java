package pro.sky.library.controller;


import org.springframework.web.bind.annotation.*;
import pro.sky.library.model.Employee;
import pro.sky.library.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String welcome() {
        return "Привет";
    }

    @GetMapping("/add")
    public Employee add( String firstName,
                         String lastName,
                         int department,
                         int salary) {
        return employeeService.addEmployee(firstName, lastName,department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(String firstName,
                           String lastName,
                           int department,
                           int salary)  {
        return employeeService.removeEmployee(firstName, lastName, department,salary);
    }

    @GetMapping("/find")
    public Employee find(String firstName,
                         String lastName,
                         int department,
                         int salary)  {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }
//    @GetMapping()
//    public List<Employee>list(){
//        return EmployeeService.list();//static?
//    }

}