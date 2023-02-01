package pl.asia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Teacher extends User {
  private Set<String> schoolSubject;
  private BigDecimal hourlyWage;

  public Teacher(int id, String firstName, String lastName, LocalDate dateOfBirth, Set<String> schoolSubject, BigDecimal hourlyWage) {
    super(id, firstName, lastName, dateOfBirth);
    this.schoolSubject = schoolSubject;
    this.hourlyWage = hourlyWage;
  }

  public Teacher(String firstName, String lastName, LocalDate dateOfBirth, Set<String> schoolSubject, BigDecimal hourlyWage) {
    super(firstName, lastName, dateOfBirth);
    this.schoolSubject = schoolSubject;
    this.hourlyWage = hourlyWage;
  }

  public Set<String> getSchoolSubject() {
    return schoolSubject;
  }

  public void setSchoolSubject(Set<String> schoolSubject) {
    this.schoolSubject = schoolSubject;
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
    return hourlyWage == teacher.hourlyWage && Objects.equals(schoolSubject, teacher.schoolSubject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), schoolSubject, hourlyWage);
  }
}
