package pl.asia.dao;

import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.sql.*;
import java.util.Iterator;

public class TeacherDao {
  private final Connection connection = ConnectionProvider.getConnection();

  public TeacherDao() {
    ConnectionProvider.getConnection();
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


  public void saveOneSubject(Teacher teacher, String subject) {
    final String sql = ("""
            INSERT IGNORE INTO subject
                    ('%s')
            VALUES
                    ('%s');
                      
                    """.formatted(subject, teacher.getId()));

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

  public void saveAllSubject(Teacher teacher) {
    for (Subject subject : teacher.getSchoolSubject()) {
      saveOneSubject(teacher, String.valueOf(subject));
    }
  }


}


