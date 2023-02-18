package pl.asia.dao;

import pl.asia.io.ConsolePrinter;
import pl.asia.model.Lesson;
import pl.asia.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LessonDao extends BaseDao implements SavingDao <Lesson>{


  @Override
  public Lesson save(Lesson lesson) {
    final String sql = String.format("""
                    INSERT INTO lesson
                    	(name,topic,local_data_time, duration,price,number_of_room, teacher_id)
                    VALUES
                    ('%s','%s','%s', %d,%d,%d, '%s');
                    """,
            lesson.getName(),lesson.getTopic(),lesson.getDate(),lesson.getDuration(),lesson.getPrice(),lesson.getNumberOfRoom(),lesson.getTeacher_id());

    try (Statement statement = getConnection().createStatement()) {
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        lesson.setId(generatedKeys.getInt(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    ConsolePrinter.printLine("Dane zapisano poprawnie");

    return new Lesson(lesson.getId(), lesson.getName(), lesson.getTeacher_id(), lesson.getTopic(),
            lesson.getDate(), lesson.getDuration(), lesson.getNumberOfRoom(), lesson.getPrice());
  }
}

