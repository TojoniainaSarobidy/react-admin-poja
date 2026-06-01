package hei.school.demo.service;

import hei.school.demo.entity.Employee;
import hei.school.demo.repository.EmployeeRepository;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> employees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.createEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    public String deleteEmployee(int idEmployee) {
        return employeeRepository.deleteEmployee(idEmployee);
    }
}
