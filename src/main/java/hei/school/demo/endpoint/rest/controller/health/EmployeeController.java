package hei.school.demo.endpoint.rest.controller.health;

import hei.school.demo.entity.Employee;
import hei.school.demo.service.EmployeeService;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public ResponseEntity<?> employees() {
        List<Employee> result = employeeService.employees();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(result);
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee result = employeeService.createEmployee(employee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(result);
    }

    @PutMapping(value = "/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        Employee result = employeeService.updateEmployee(employee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(result);
    }

    @DeleteMapping(value = "/employee/{idEmployee}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int idEmployee) {
        String result = employeeService.deleteEmployee(idEmployee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(result);
    }
}
