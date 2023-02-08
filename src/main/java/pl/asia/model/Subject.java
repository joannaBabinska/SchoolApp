package pl.asia.model;

public enum Subject {
  MATH(1, "Matematyka"),
  POLISH_LANGUAGE(2, "Język Poolski"),
  ENGLISH_LANGUAGE(3, "Język Angielski");

  private final int code;
  private final String fullName;

  Subject(final int code, final String fullName) {
    this.code = code;
    this.fullName = fullName;
  }

  public int getCode() {
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
      System.out.println(value.fullName + " numer -> " + value.getCode());
    }
  }

}
