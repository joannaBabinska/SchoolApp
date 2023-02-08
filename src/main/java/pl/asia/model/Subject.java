package pl.asia.model;

public enum Subject {
  MATH("MAT", "Matematyka", 1),
  POLISH_LANGUAGE("POL", "Język Polski", 2),
  ENGLISH_LANGUAGE("ENG", "Język Angielski", 3),
  INFORMS("IT", "Informatyka", 4),
  GEOGRAPHY("GEO", "Geografia",5),
  BIOLOGY("BIO","Bilogia",6),
  CHEMISTRY("CHM","Chemia",7);

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

  public static void printSubject() {
    for (Subject value : Subject.values()) {
      System.out.println(value.fullName + " numer -> " + value.getNumber());
    }
  }

}
