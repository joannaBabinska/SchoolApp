package pl.asia.dao;

import pl.asia.io.ConsolePrinter;
import pl.asia.model.Lesson;
import pl.asia.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LessonDao extends BaseDao implements SavingDao{


  @Override
  public Object save(Object entity) {
    final String sql = String.format("""
                      INSERT INTO teacher
                      (hourly_wage, first_name, last_name,date_of_birth)
                      VALUES (%s, '%s', '%s', '%s');""",
            teacher.getHourlyWage(), teacher.getFirstName(), teacher.getLastName(), teacher.getDateOfBirth());

    try (Statement statement = getConnection().createStatement()) {
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        teacher.setId(generatedKeys.getInt(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    teacher.getSchoolSubject().forEach(subject -> {
      addSubjectMethod(subject, teacher);
    });

    ConsolePrinter.printLine("Dane zapisano poprawnie");

    return new Teacher(teacher.getId(), teacher.getFirstName(), teacher.getFirstName(),
            teacher.getDateOfBirth(), teacher.getSchoolSubject(), teacher.getHourlyWage());
  }
}
}
