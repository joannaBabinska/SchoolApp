package pl.asia.model;

import java.util.Arrays;

public enum Subject {
  MATH("MAT", "Matematyka", 1),
  POLISH_LANGUAGE("POL", "Język Polski", 2),
  ENGLISH_LANGUAGE("ENG", "Język Angielski", 3),
  INFORMS("IT", "Informatyka", 4),
  GEOGRAPHY("GEO", "Geografia", 5),
  BIOLOGY("BIO", "Bilogia", 6),
  CHEMISTRY("CHM", "Chemia", 7);

  private final String code;
  private final String fullName;
  private final int number;

  Subject(final String code, final String fullName, int number) {
    this.code = code;
    this.fullName = fullName;
    this.number = number;
  }

  public String getCode() {
    return code;
  }

  public String getFullName() {
    return fullName;
  }

  public int getNumber() {
    return number;
  }

  public static void printSubjectForChooseLoop() {
    for (Subject value : Subject.values()) {
      System.out.println(value.fullName + " numer -> " + value.getNumber());
    }
  }

  public static void printSubjectInfo(Teacher teacher) {
    teacher.getSchoolSubject().forEach(subject -> System.out.println(subject.fullName));

  }

  public static Subject makeSubjectFromStringCode(String code) {
    return Arrays.stream(Subject.values())
            .filter(subCode -> subCode.getCode().equalsIgnoreCase(code))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Subject not found " + code));
  }

  public static Subject makeSubjectFromIntNumber(int number) {
    return Arrays.stream(Subject.values())
            .filter(subNumb -> subNumb.getNumber() == number)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Subject not found " + number));


  }
}
