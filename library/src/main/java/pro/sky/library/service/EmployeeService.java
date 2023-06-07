package pro.sky.library.service;


import org.springframework.stereotype.Service;
import pro.sky.library.exception.EmployeeAlreadyAddedException;
import pro.sky.library.exception.EmployeeStorageIsFullException;
import pro.sky.library.exception.EmployeeNotFoundException;
import pro.sky.library.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 4;
    private final List<Employee> employee = new ArrayList<>(SIZE);
    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    public List<Employee> list() {
        return Collections.unmodifiableList(employee);
    }


    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        Employee employeeNew = new Employee(validatorService.validatorName(firstName), validatorService.validatorSurname(lastName), department, salary);
        if (employee.size() < SIZE)
            for (Employee q : employee) {
                if (q.equals(employeeNew)) {
                    throw new EmployeeAlreadyAddedException();
                }
                employee.add(employeeNew);
                return employeeNew;

            }
        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String firstName, String lastName, int department, int salary) {
        Employee employeeNew = new Employee(firstName, lastName, department, salary);
        if (employee.remove(employeeNew)) {
            return employeeNew;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String firstName, String lastName, int department, int salary) {
        Employee employeeNew = new Employee(firstName, lastName, department, salary);
        if (employee.contains(employeeNew)) {
            return employeeNew;
        }
        throw new EmployeeNotFoundException();
    }
    public List<Employee> getAll() {
        return new ArrayList<>(employee);
    }


}
