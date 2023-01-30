public class Teacher extends User{
  private String schoolSubject;
  private int hourlyWage;

  public Teacher(String firstName, String lastName, int age, String schoolSubject, int hourlyWage) {
    super(firstName, lastName, age);
    this.schoolSubject = schoolSubject;
    this.hourlyWage = hourlyWage;
  }

  public String getSchoolSubject() {
    return schoolSubject;
  }

  public void setSchoolSubject(String schoolSubject) {
    this.schoolSubject = schoolSubject;
  }

  public int getHourlyWage() {
    return hourlyWage;
  }

  public void setHourlyWage(int hourlyWage) {
    this.hourlyWage = hourlyWage;
  }
}
