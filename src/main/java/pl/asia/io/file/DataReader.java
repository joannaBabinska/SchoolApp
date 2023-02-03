package pl.asia.io.file;

import pl.asia.model.Teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DataReader {
  private Scanner scanner = new Scanner(System.in);
  private ConsolePrinter consolePrinter;

  public DataReader(ConsolePrinter consolePrinter) {
    this.consolePrinter = consolePrinter;
  }

  public Teacher addTeacher() {
    consolePrinter.printLine("Wprowadź dane nowego nauczyciela");
    consolePrinter.printLine("Podaj imię");
    String firstName = scanner.nextLine();
    consolePrinter.printLine("Podaj nazwisko");
    String lastName = scanner.nextLine();
    consolePrinter.printLine("Podaj date urodzenia w formacie YYYY-MM-DD");
    String dateOfBirth = scanner.nextLine();
    LocalDate localDateDateOfBirth = LocalDate.parse(dateOfBirth);
    consolePrinter.printLine("Podaj liczbę przedmiotów, których może uczyć ten nauczyciel");
    int numberOfSchoolSubject = scanner.nextInt();
    scanner.nextLine();
    Set<String> subjectSet = createSubjectSet(numberOfSchoolSubject);
    consolePrinter.printLine("Podaj stawkę godzinową");
    int hourlyWage = scanner.nextInt();
    return new Teacher(firstName, lastName, localDateDateOfBirth, subjectSet, BigDecimal.valueOf(hourlyWage));
  }

  private Set<String> createSubjectSet(int numberOfSchoolSubject) {
    Set<String> schoolSubject = new HashSet<>();
    do {
      consolePrinter.printLine("Podaj nazwę jednego przedmiotu");
      String subject = scanner.nextLine();
      schoolSubject.add(subject);
    } while (!(schoolSubject.size() == numberOfSchoolSubject));
    return schoolSubject;
  }

  public int getInt() {
    try {
      return scanner.nextInt();
    } finally {
      scanner.nextLine();
    }
  }

  void addSubjectToTeacher(Teacher teacher) {
    Subject subject;
    do {
      consolePrinter.printLine("Ilu przedmiotów może uczyć ten nauczyciel?");
      int iteration = insertInt();

      consolePrinter.printLine("Wybierz przedmiot wśród wyświetlonych");
      printSubject();
      for (int i = 0; i <iteration+1 ; i++) {
        teacher.setSchoolSubject(insertString());
      }



    }}

  private int insertInt() {
    int i = scanner.nextInt();

    scanner.nextLine();
    return i;
  }

  private String insertString() {
    return scanner.nextLine();
  }

  private void printSubject() {

      for (Subject value : Subject.values()) {
        System.out.println(value);
      }

    }

  }

  enum Subject {
    MATH("MAT", "Matematyka"),
    POLISH_LANGUAGE("POL", "Język Poolski"),
    ENGLISH_LANGUAGE("ENG", "Język Angielski");

    private final String code;
    private final String fullName;

    Subject(final String code, final String fullName) {
      this.code = code;
      this.fullName = fullName;
    }


  }


}
};

        }
