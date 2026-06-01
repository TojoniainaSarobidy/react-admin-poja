package hei.school.demo.repository;

import hei.school.demo.configuration.DBConnection;
import hei.school.demo.entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
  public List<Employee> getAllEmployees() {
    DBConnection dbConnection = new DBConnection();
    List<Employee> employees = new ArrayList<>();
    try (Connection connection = dbConnection.getDBConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement(
                "SELECT id, firstname, lastname, email, department, salary, active FROM"
                    + " employee")) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirstname(resultSet.getString("firstname"));
        employee.setLastname(resultSet.getString("lastname"));
        employee.setEmail(resultSet.getString("email"));
        employee.setDepartment(resultSet.getString("department"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setActive(resultSet.getBoolean("active"));
        employees.add(employee);
      }
      return employees;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
