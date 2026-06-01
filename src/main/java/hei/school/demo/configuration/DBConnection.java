package hei.school.demo.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class DBConnection {
  private final Dotenv dotenv = Dotenv.load();

  public Connection getDBConnection() {
    try {
      String jdbc_url = dotenv.get("jdbc_url");
      String user = dotenv.get("user");
      String password = dotenv.get("password");
      return DriverManager.getConnection(jdbc_url, user, password);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void getCloseConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
