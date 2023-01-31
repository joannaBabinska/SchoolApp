package pl.asia.model;

import java.time.LocalDateTime;

public class Lesson {
  String id;
  String topic;
  LocalDateTime date;
  Teacher teacher;
  Set<Student> students;
  int numberOfRoom;

}
/*
dodac biblioteke Lombok jako zaleznosc do mavena
prepared statement zamiast statement

//add new student
insert into student
insert into student

//add new teacher
insert into teacher

//add new lesson
insert into lesson values (id, topic, date, teacherId, numberOfRoom)
insert into student_lesson(lessonId, studentId)

//print all student

//print all teachers

//print all lessons short info

//print lesson with details by id
 */

/*
LESSON
        id|topic|teacher_id
        1|"przywutaeni"|12

        LESSON_STUDENT
        lessonid|studentID
        1|1
        1|2

        STUDENT
        id|name
        1|"Mati"
        2|"Asia"
 */