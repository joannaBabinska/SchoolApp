package pl.asia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

  public static final String URL = "jdbc:mysql://localhost:3306/schoolapp";
  public static final String USER = "root";
  public static final String PASSWORD = "admin";

  public static Connection getConnection() {
    try{
      return DriverManager.getConnection(URL,USER,PASSWORD);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
