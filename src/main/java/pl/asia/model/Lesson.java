package pl.asia.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Lesson {
  private String id;
  private Teacher teacher;
  private Set<Student> students;
  private String topic;
  private LocalDateTime date;
  private int duration;
  private int numberOfRoom;
  private int price;

  public Lesson(String id, Teacher teacher, Set<Student> students, String topic,
                LocalDateTime date, int duration, int numberOfRoom, int price) {
    this.id = id;
    this.teacher = teacher;
    this.students = students;
    this.topic = topic;
    this.date = date;
    this.duration = duration;
    this.numberOfRoom = numberOfRoom;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getNumberOfRoom() {
    return numberOfRoom;
  }

  public void setNumberOfRoom(int numberOfRoom) {
    this.numberOfRoom = numberOfRoom;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
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