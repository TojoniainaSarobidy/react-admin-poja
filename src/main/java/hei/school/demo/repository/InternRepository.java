package hei.school.demo.repository;

import hei.school.demo.configuration.DBConnection;
import hei.school.demo.entity.Employee;
import hei.school.demo.entity.Intern;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InternRepository {
  public List<Intern> getAllInterns() {
    DBConnection dbConnection = new DBConnection();
    List<Intern> interns = new ArrayList<>();
    try (Connection connection = dbConnection.getDBConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement(
                "SELECT i.id AS intern_id, i.firstname AS intern_firstname, i.lastname AS"
                    + " intern_lastname, i.email AS intern_email, school, amount, e.id AS"
                    + " employee_id, e.firstname AS employee_firstname, e.lastname AS"
                    + " employee_lastname, e.email AS employee_email, department, salary, active"
                    + " FROM intern i INNER JOIN employee e on i.manager_id = e.id")) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        List<Employee> employees = new ArrayList<>();
        Intern intern = new Intern();
        intern.setId(resultSet.getInt("intern_id"));
        intern.setFirstname(resultSet.getString("intern_firstname"));
        intern.setLastname(resultSet.getString("intern_lastname"));
        intern.setEmail(resultSet.getString("intern_email"));
        intern.setSchool(resultSet.getString("school"));
        intern.setAmount(resultSet.getDouble("amount"));

        Employee employee = new Employee();
        employee.setId(resultSet.getInt("employee_id"));
        employee.setFirstname(resultSet.getString("employee_firstname"));
        employee.setLastname(resultSet.getString("employee_lastname"));
        employee.setEmail(resultSet.getString("employee_email"));
        employee.setDepartment(resultSet.getString("department"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setActive(resultSet.getBoolean("active"));

        employees.add(employee);
        intern.setEmployee(employees);
        interns.add(intern);
      }
      return interns;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Intern createIntern(Intern intern, int idEmployee) {
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getDBConnection();
    try {
      connection.setAutoCommit(false);
      try (PreparedStatement preparedStatement =
          connection.prepareStatement(
              "INSERT INTO intern (id, manager_id, firstname, lastname, email, school, amount)"
                  + " VALUES (?,?,?,?,?,?,?) RETURNING id")) {
        preparedStatement.setInt(1, intern.getId());
        preparedStatement.setInt(2, idEmployee);
        preparedStatement.setString(3, intern.getFirstname());
        preparedStatement.setString(4, intern.getLastname());
        preparedStatement.setString(5, intern.getEmail());
        preparedStatement.setString(6, intern.getSchool());
        preparedStatement.setDouble(7, intern.getAmount());
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          if (resultSet.next()) {
            resultSet.getInt("id");
          }
          connection.commit();
          return intern;
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

  public Intern updateIntern(Intern intern, int idEmployee) {
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getDBConnection();
    try {
      connection.setAutoCommit(false);
      try (PreparedStatement preparedStatement =
          connection.prepareStatement(
              "UPDATE intern SET manager_id = ?, firstname = ?, lastname = ?, email = ?, school ="
                  + " ?, amount = ? WHERE id = ? RETURNING id")) {
        preparedStatement.setInt(1, idEmployee);
        preparedStatement.setString(2, intern.getFirstname());
        preparedStatement.setString(3, intern.getLastname());
        preparedStatement.setString(4, intern.getEmail());
        preparedStatement.setString(5, intern.getSchool());
        preparedStatement.setDouble(6, intern.getAmount());
        preparedStatement.setInt(7, intern.getId());
        try (ResultSet resultSet = preparedStatement.executeQuery(); ) {
          if (resultSet.next()) {
            resultSet.getInt("id");
          }
          connection.commit();
          return intern;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      dbConnection.getCloseConnection(connection);
    }
  }

  public String deleteIntern(int idIntern) {
    DBConnection dbConnection = new DBConnection();
    try (Connection connection = dbConnection.getDBConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement("DELETE FROM intern WHERE id = ?")) {
      preparedStatement.setInt(1, idIntern);
      preparedStatement.executeUpdate();
      return "Intern deleted successfully";
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
