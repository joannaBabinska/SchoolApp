package pl.asia.dao;

import pl.asia.model.Student;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

public class StudentDao extends BaseDao implements SavingDao <Student>{


  public void enrollAStudentInAClass(String FullName){

  }

  public Optional<Student> findStudentByFullName(String fullName) {
    String[] firstNameAndLastName= fullName.split(" ");
    String firstName = firstNameAndLastName[0];
    String lastName = firstNameAndLastName[1];
    int id = -1;
    LocalDate dateOfBirth = null;
    int grade = -1;
    String school = null;
    final String sql = String.format("""
            SELECT
                id,first_name, last_name, date_of_birth, hourly_wage
            FROM
                teacher
            WHERE
                first_name = '%s' AND last_name = '%s'; ""
            
            """,firstName,lastName);

    try (Statement statement = getConnection().createStatement()) {

      ResultSet resultSetT = statement.executeQuery(sql);
      if (resultSetT.next()) {
        id = resultSetT.getInt("id");
        dateOfBirth = LocalDate.parse(resultSetT.getString("date_of_birth"));
        grade = resultSetT.getInt("grade");
        school = resultSetT.getString("school");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


//    TODO

    return Optional.of(new Student(id,firstName,lastName,dateOfBirth,grade,school));
  }

  @Override
  public Student save(Student student) {
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
    return new Student(student.getId(),student.getFirstName(),student.getLastName(),student.getDateOfBirth(),
            student.getGrade(),student.getSchool());
  }
}
