package pl.asia.dao;


import pl.asia.io.file.ConsolePrinter;
import pl.asia.io.file.DataReader;
import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TeacherDao extends BaseDao {
  //  private final Connection connection = ConnectionProvider.getConnection();
  private final ConsolePrinter consolePrinter = new ConsolePrinter();
  private final DataReader dataReader = new DataReader(consolePrinter);

  public void close() {
    try {
      getConnection().close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  public void saveTeacherToDatabase(Teacher teacher) {
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
  }

  public void saveSubjectToDatabase(Teacher teacher) {
    teacher.getSchoolSubject().forEach(subject -> {
      addSubjectMethod(subject, teacher);
    });
  }

  private void addSubjectMethod(Subject subject, Teacher teacher) {
    final String sql = String.format("""
                    INSERT  INTO teacher_has_subject
                    	(subject_code, teacher_id)
                    VALUES
                    	('%s',  %d);""",
            subject.getCode(), teacher.getId());

    try (Statement statement = getConnection().createStatement()) {
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> takeAllTeachersNamesFromDatabase() {
    List<String> firstAndLastNames = new ArrayList<>();
    final String sql = """
            SELECT first_name, last_name FROM teacher;""";
    try (Statement statement = getConnection().createStatement()) {
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        firstAndLastNames.add(firstName + " " + lastName);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return firstAndLastNames;
  }


  public Optional findTeacherByName(String name) {
    int teacherId = -1;
    String firstName = null;
    String lastName = null;
    String dateOfBirth = null;
    BigDecimal hourlyWage = BigDecimal.valueOf(0.0);
    String[] fullName = name.split(" ");
    final String sqlT = String.format("""
            SELECT
                first_name, last_name, date_of_birth, hourly_wage FROM teacher
            WHERE
                first_name = '%s' AND last_name = '%s'; """, fullName[0], fullName[1]);
    final String sqlS = String.format("""
            SELECT * FROM teacher_has_subject WHERE teacher_id = '%d'""", teacherId);
    try (Statement statement = getConnection().createStatement()) {

      ResultSet resultSetT = statement.executeQuery(sqlT);
      if (resultSetT.next()) {
        teacherId = resultSetT.getInt("id");
        firstName = resultSetT.getString("first_name");
        lastName = resultSetT.getString("last_name");
        dateOfBirth = resultSetT.getString("date_of_birth");
        hourlyWage = BigDecimal.valueOf(resultSetT.getInt("hourly_wage"));
//        return Optional.of(new Teacher(id,firstName,lastName,dateOfBirth,subjects,hourlyWage);
      }

      ResultSet resultSetS = statement.executeQuery(sqlS);
      Set<Subject> subjects = new HashSet<>();

      while (resultSetS.next()) {
        String subjectCode = resultSetT.getString("subject_code");
        Subject subject = makeSubjectFromStringCode(subjectCode);
        subjects.add(subject);
      }

      return Optional.of(new Teacher(teacherId, firstName, lastName, LocalDate.parse(dateOfBirth), subjects, hourlyWage));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Subject makeSubjectFromStringCode(String code) {
    return Arrays.stream(Subject.values())
            .filter(subCode -> subCode.getCode().equalsIgnoreCase(code))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Subject not found " + code));
  }

}



