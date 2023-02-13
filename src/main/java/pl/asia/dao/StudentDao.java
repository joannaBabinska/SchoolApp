package pl.asia.dao;

import pl.asia.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDao extends BaseDao {

  public void saveStudentToDatabase(Student student) {
    final String sql = String.format("""
                    INSERT INTO student
                      (first_name, last_name,date_of_birth, grade, school)
                      VALUES ('%s', '%s', '%s', %d,'%s');""",
            student.getFirstName(), student.getLastName(), student.getDateOfBirth(),student.getGrade(),student.getSchool());

    try (Statement statement = getConnection().createStatement()) {
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        student.setId(generatedKeys.getInt(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
