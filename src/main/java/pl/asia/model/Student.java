package pl.asia.model;

import java.util.Date;
import java.util.Objects;

public class Student extends User{
  private int grade;
  private String school;

  public Student(int id, String firstName, String lastName, Date dateOfBirth, int grade, String school) {
    super(id, firstName, lastName, dateOfBirth);
    this.grade = grade;
    this.school = school;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Student student = (Student) o;
    return grade == student.grade && Objects.equals(school, student.school);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), grade, school);
  }
}
