package pro.sky.library.service;

import org.springframework.stereotype.Service;
import pro.sky.library.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void changeDepartment(Employee employee, int newDepartment) {
        employeeService.getAll().stream()
                .filter(value -> Objects.equals(employee, value))
                .findFirst()
                .ifPresent(value -> value.setDepartment(newDepartment));
    }

    public void indexSalariesForDepartment(double index, int department) {
        employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> employee.setSalary(
                        (int) (employee.getSalary() + employee.getSalary() * index / 100)
                ));
    }

    public double averageSalaryForDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0);
    }

    public double totalSalariesForDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee findEmployeeWithMinSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee findEmployeeWithMaxSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> findAllEmployeesFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList();
    }
}

