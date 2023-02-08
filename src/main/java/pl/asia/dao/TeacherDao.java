package pl.asia.dao;


import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.sql.*;

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
//    for (int i = 0; i < teacher.getSchoolSubject().size(); i++) {
//      Subject nextSchoolSubject = teacher.getNextSchoolSubject();
//      final String sql = String.format("""
//                      INSERT  INTO teacher_has_subject
//                      	(subject_code, subject_name, teacher_id)
//                      VALUES
//                      	(%d, '%s' , %d);""",
//              nextSchoolSubject.getCode(), nextSchoolSubject.getFullName(), teacher.getId());
//
//      try (Statement statement = getConnection().createStatement()) {
//        statement.executeUpdate(sql/*, Statement.RETURN_GENERATED_KEYS*/);
////      ResultSet generatedKeys = statement.getGeneratedKeys();
////      if (generatedKeys.next()) {
////        teacher.setId(generatedKeys.getInt(1));
//      } catch (SQLException e) {
//        throw new RuntimeException(e);
//      }
//    }
  }

  private void addSubjectMethod(Subject subject,Teacher teacher) {
    final String sql = String.format("""
                      INSERT  INTO teacher_has_subject
                      	(subject_code, subject_name, teacher_id)
                      VALUES
                      	(%d, '%s' , %d);""",
            subject.getCode(), subject.getFullName(), teacher.getId());

    try (Statement statement = getConnection().createStatement()) {
      statement.executeUpdate(sql/*, Statement.RETURN_GENERATED_KEYS*/);
//      ResultSet generatedKeys = statement.getGeneratedKeys();
//      if (generatedKeys.next()) {
//        teacher.setId(generatedKeys.getInt(1));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
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





