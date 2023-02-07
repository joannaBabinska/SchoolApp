package pl.asia.model;

public enum Subject {
  MATH("MAT", "Matematyka"),
  POLISH_LANGUAGE("POL", "Język Poolski"),
  ENGLISH_LANGUAGE("ENG", "Język Angielski");

  private final String code;
  private final String fullName;

  Subject(final String code, final String fullName) {
    this.code = code;
    this.fullName = fullName;
  }

  public String getCode() {
    return code;
  }

  public String getFullName() {
    return fullName;
  }

  public static Subject fromFullName(String fullName) {
    Subject[] values = Subject.values();
    for (Subject subject : values) {
      if (subject.getFullName().equalsIgnoreCase(fullName))
        return subject;
    }
    throw new RuntimeException("SUBJECT NOT FOUND " + fullName);
//    return null;// wyjątek aalno optionala
  }

  public static void printSubject() {
    for (Subject value : Subject.values()) {
      System.out.println(value.fullName);
    }
  }

}
