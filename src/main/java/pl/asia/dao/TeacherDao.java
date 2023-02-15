package pl.asia.dao;


import pl.asia.io.ConsolePrinter;
import pl.asia.io.DataReader;
import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TeacherDao extends BaseDao implements SavingDao<Teacher>, FindingDao<Teacher> {


  private final ConsolePrinter consolePrinter = new ConsolePrinter();
  private final DataReader dataReader = new DataReader(consolePrinter);

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


  public String findTeacherByName(String name) {
    String[] fullName = name.split(" ");
    String allInformationFromDatabaseAboutFromTeacher = null;

    final String sqlT = String.format("""
            SELECT
                id,first_name, last_name, date_of_birth, hourly_wage
            FROM
                teacher
            WHERE
                first_name = '%s' AND last_name = '%s'; """, fullName[0], fullName[1]);

    try (Statement statement = getConnection().createStatement()) {

      ResultSet resultSetT = statement.executeQuery(sqlT);
      if (resultSetT.next()) {
        int teacherId = resultSetT.getInt("id");
        String firstName = resultSetT.getString("first_name");
        String lastName = resultSetT.getString("last_name");
        String dateOfBirth = resultSetT.getString("date_of_birth");
        BigDecimal hourlyWage = BigDecimal.valueOf(resultSetT.getInt("hourly_wage"));
        allInformationFromDatabaseAboutFromTeacher =
                teacherId + " " + firstName + " " + lastName + " " + dateOfBirth + " " + hourlyWage;
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return allInformationFromDatabaseAboutFromTeacher;
  }


  public Optional<Teacher> findSubjectForTeacher(String allInformationFromDatabaseAboutFromTeacher) {
    String[] baseInformation = allInformationFromDatabaseAboutFromTeacher.split(" ");
    int teacherId = Integer.parseInt(baseInformation[0]);
    String firstName = baseInformation[1];
    String lastName = baseInformation[2];
    LocalDate dateOfBirth = LocalDate.parse(baseInformation[3]);
    BigDecimal hourlyWage = BigDecimal.valueOf(Integer.parseInt(baseInformation[4]));

    Set<Subject> subjects = new HashSet<>();

    try (Statement statement = getConnection().createStatement()) {

      final String sql = String.format("""
              SELECT * FROM teacher_has_subject WHERE teacher_id = '%d'""", teacherId);

      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        String subjectCode = resultSet.getString("subject_code");
        Subject subject = makeSubjectFromStringCode(subjectCode);
        subjects.add(subject);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return Optional.of(new Teacher(teacherId, firstName, lastName, dateOfBirth, subjects, hourlyWage));

  }

  ;


  public Subject makeSubjectFromStringCode(String code) {
    return Arrays.stream(Subject.values())
            .filter(subCode -> subCode.getCode().equalsIgnoreCase(code))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Subject not found " + code));
  }

  @Override
  public Teacher save(Teacher teacher) {
    final String sql = String.format("""
                    INSERT INTO teacher
                    (hourly_wage, first_name, last_name,date_of_birth)
                    VALUES (%s, '%s', '%s', '%s');""",
            teacher.getHourlyWage(), teacher.getFirstName(), teacher.getLastName(), teacher.getDateOfBirth());

    try (Statement statement = getConnection().createStatement()) {
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();

      teacher.getSchoolSubject().forEach(subject -> {
        addSubjectMethod(subject, teacher);

      });
      if (generatedKeys.next()) {
        teacher.setId(generatedKeys.getInt(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return new Teacher(teacher.getId(), teacher.getFirstName(), teacher.getFirstName(),
            teacher.getDateOfBirth(), teacher.getSchoolSubject(), teacher.getHourlyWage());
  }

  @Override
  public Optional<Teacher> FindByName(String name) {
    int teacherId = -1;
    String firstName = null;
    String lastName = null;
    LocalDate dateOfBirth = null;
    BigDecimal hourlyWage = null;

    String[] fullName = name.split(" ");

    final String sqlT = String.format("""
            SELECT
                id,first_name, last_name, date_of_birth, hourly_wage
            FROM
                teacher
            WHERE
                first_name = '%s' AND last_name = '%s'; """, fullName[0], fullName[1]);

    try (Statement statement = getConnection().createStatement()) {

      ResultSet resultSetT = statement.executeQuery(sqlT);
      if (resultSetT.next()) {
        teacherId = resultSetT.getInt("id");
        firstName = resultSetT.getString("first_name");
        lastName = resultSetT.getString("last_name");
        dateOfBirth = LocalDate.parse(resultSetT.getString("date_of_birth"));
        hourlyWage = BigDecimal.valueOf(resultSetT.getInt("hourly_wage"));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    Set<Subject> subjects = new HashSet<>();

    try (Statement statement = getConnection().createStatement()) {

      final String sql = String.format("""
              SELECT * FROM teacher_has_subject WHERE teacher_id = '%d'""", teacherId);

      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        String subjectCode = resultSet.getString("subject_code");
        Subject subject = makeSubjectFromStringCode(subjectCode);
        subjects.add(subject);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return Optional.of(new Teacher(teacherId, firstName, lastName, dateOfBirth, subjects, hourlyWage));

  }
}



