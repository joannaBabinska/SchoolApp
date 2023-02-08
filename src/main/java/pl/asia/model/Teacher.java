package pl.asia.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Teacher extends User {
  private Set<Subject> schoolSubject;
  private BigDecimal hourlyWage;

  public Teacher(int id, String firstName, String lastName, LocalDate dateOfBirth, Set<Subject> schoolSubject, BigDecimal hourlyWage) {
    super(id, firstName, lastName, dateOfBirth);
    this.schoolSubject = schoolSubject;
    this.hourlyWage = hourlyWage;
  }

  public Teacher(String firstName, String lastName, LocalDate dateOfBirth, Set<Subject> schoolSubject, BigDecimal hourlyWage) {
    super(firstName, lastName, dateOfBirth);
    this.schoolSubject = schoolSubject;
    this.hourlyWage = hourlyWage;
  }

  public Set<Subject> getSchoolSubject() {
    return schoolSubject;
  }
  public Subject getNextSchoolSubject() {
    return schoolSubject.iterator().next();
  }

  public void setSchoolSubject(Subject subject) {
    this.schoolSubject.add(subject);
  }

  public BigDecimal getHourlyWage() {
    return hourlyWage;
  }

  public void setHourlyWage(BigDecimal hourlyWage) {
    this.hourlyWage = hourlyWage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Teacher teacher = (Teacher) o;
    return Objects.equals(schoolSubject, teacher.schoolSubject) && Objects.equals(hourlyWage, teacher.hourlyWage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), schoolSubject, hourlyWage);
  }
}
