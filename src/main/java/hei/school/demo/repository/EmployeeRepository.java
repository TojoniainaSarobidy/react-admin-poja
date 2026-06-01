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

import javax.xml.transform.Result;

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

    public Employee createEmployee(Employee employee) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getDBConnection();
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employee(id, firstname, lastname, email, department, salary, active) VALUES (?,?,?,?,?,?,?) RETURNING id"
            )) {
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setString(2, employee.getFirstname());
                preparedStatement.setString(3, employee.getLastname());
                preparedStatement.setString(4, employee.getEmail());
                preparedStatement.setString(5, employee.getDepartment());
                preparedStatement.setDouble(6, employee.getSalary());
                preparedStatement.setBoolean(7, employee.getActive());
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        resultSet.getInt("id");
                    }
                    connection.commit();
                    return employee;
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.getCloseConnection(connection);
        }
    }

    public Employee updateEmployee(Employee employee) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getDBConnection();
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE employee SET firstname = ?, lastname = ?, email = ?, department = ?, salary = ?, active = ? WHERE id = ? RETURNING id"
            )) {
                preparedStatement.setString(1, employee.getFirstname());
                preparedStatement.setString(2, employee.getLastname());
                preparedStatement.setString(3, employee.getEmail());
                preparedStatement.setString(4, employee.getDepartment());
                preparedStatement.setDouble(5, employee.getSalary());
                preparedStatement.setBoolean(6, employee.getActive());
                preparedStatement.setInt(7, employee.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) {
                        resultSet.getInt("id");
                    }
                    connection.commit();
                    return employee;
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.getCloseConnection(connection);
        }
    }

    public String deleteEmployee(int idEmployee) {
        DBConnection dbConnection = new DBConnection();
        try (Connection connection = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id = ?"
             )) {
            preparedStatement.setInt(1, idEmployee);
            preparedStatement.executeUpdate();
            return "Employee deleted successfully";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
