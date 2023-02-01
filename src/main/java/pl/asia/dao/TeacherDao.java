package pl.asia.dao;

import pl.asia.model.Teacher;

import java.sql.*;
import java.util.Iterator;

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
                    INSERT INTO teacher
                    (hourly_wage, first_name, last_name,date_of_birth)
                    VALUES (%s, '%s', '%s', '%s');""",
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

  }

  public void saveSubject(Teacher teacher) {
    StringBuilder sb = new StringBuilder();
    final String sql = sb.toString();


    for (String s : teacher.getSchoolSubject()) {
      sb.append("""
              INSERT IGNORE INTO subject
                      (name)
              VALUES
                      ('%s');""".formatted(s));

    }

    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        teacher.setId(generatedKeys.getInt(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


  }
}

