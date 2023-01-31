package pl.asia.model;

import java.util.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Teacher teacher = (Teacher) o;
    return hourlyWage == teacher.hourlyWage && Objects.equals(schoolSubject, teacher.schoolSubject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), schoolSubject, hourlyWage);
  }
}
