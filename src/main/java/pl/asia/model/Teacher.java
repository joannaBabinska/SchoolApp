package pl.asia.model;

import java.util.Set;

public class Teacher extends User {
  private Set<String> schoolSubject;
  private int hourlyWage;

  public Teacher(int id, String firstName, String lastName, int age, Set<String> schoolSubject, int hourlyWage) {
    super(id, firstName, lastName, age);
    this.schoolSubject = schoolSubject;
    this.hourlyWage = hourlyWage;
  }

  public Set<String> getSchoolSubject() {
    return schoolSubject;
  }

  public void setSchoolSubject(Set<String> schoolSubject) {
    this.schoolSubject = schoolSubject;
  }

  public int getHourlyWage() {
    return hourlyWage;
  }

  public void setHourlyWage(int hourlyWage) {
    this.hourlyWage = hourlyWage;
  }
}
