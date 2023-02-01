package pl.asia.dao;

import pl.asia.model.Teacher;

import java.sql.*;

public class TeacherDao {
  private final Connection connection;

  public TeacherDao() {
    try {
      this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolapp", "root", "admin");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void save(Teacher teacher) {
    final String sql = String.format("""
                    INSERT INTO Teacher
                      (hourly_wage, first_name, last_name,date_of_birth)
                    VALUES ('%s', '%s', '%s', '%s'""",
            teacher.getHourlyWage(), teacher.getFirstName(), teacher.getLastName(), teacher.getDateOfBirth());

    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        teacher.setId(generatedKeys.getInt(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }}
