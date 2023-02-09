package pl.asia.dao;


import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends BaseDao {
//  private final Connection connection = ConnectionProvider.getConnection();


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
      statement.executeUpdate(sql/*, Statement.RETURN_GENERATED_KEYS*/);
//      ResultSet generatedKeys = statement.getGeneratedKeys();
//      if (generatedKeys.next()) {
//        teacher.setId(generatedKeys.getInt(1));
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
        firstAndLastNames.add(firstName + " " +  lastName);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return firstAndLastNames;
  }

  public void printAllTeachers() {
    List<String> names = takeAllTeachersNamesFromDatabase();
    names.forEach(System.out::println);
  }
}


//  public void saveOneSubject(Teacher teacher, Subject subject) {
//    final String sql = ("""
//            INSERT IGNORE INTO subject
//                    ('%s')
//            VALUES
//                    ('%s');
//
//                    """.formatted(subject., teacher.getId()));
//    System.out.println(sql);

//    try (Statement statement = getConnection().createStatement()) {
//      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//      ResultSet generatedKeys = statement.getGeneratedKeys();
//      if (generatedKeys.next()) {
//        teacher.setId(generatedKeys.getInt(1));
//      }
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//  }

//  public void saveAllSubject(Teacher teacher) {
//    for (Subject subject : teacher.getSchoolSubject()) {
//      saveOneSubject(teacher, subject);
//    }





