package pl.asia.io.file;

import pl.asia.app.ControlLoop;
import pl.asia.exception.NoSuchOptionException;
import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class DataReader {
  private Scanner scanner = new Scanner(System.in);
  private ConsolePrinter consolePrinter;

  public DataReader(ConsolePrinter consolePrinter) {
    this.consolePrinter = consolePrinter;
  }

  public Teacher enterTeacher() {
    consolePrinter.printLine("Wprowadź dane nowego nauczyciela");
    consolePrinter.printLine("Podaj imię");
    String firstName = getString();
    consolePrinter.printLine("Podaj nazwisko");
    String lastName = getString();
    consolePrinter.printLine("Podaj datę urodzenia w formacie YYYY-MM-DD");// obsłuż wyjątek
    String dateOfBirth = getString();
    LocalDate localDateDateOfBirth = LocalDate.parse(dateOfBirth);
    System.out.println("Jakiego przedmiotu może uczyć?");
    Set<Subject> subjects = enterSubjects();
    consolePrinter.printLine("Podaj stawkę godzinową");
    int hourlyWage = getInt();
    return new Teacher(firstName, lastName, localDateDateOfBirth, subjects, BigDecimal.valueOf(hourlyWage));
  }

  private Set<Subject> enterSubjects() {
    Set<Subject> subjects = new HashSet<>();
    chooseSubjectToEnter(subjects);
     AddMoreSubjectOrExitLoop(subjects);

    return subjects;
  }

  private Set<Subject> AddMoreSubjectOrExitLoop(Set<Subject> subjects) {
    int option;
    do {
      printAddMoreSubjectOrExit();
      option = getInt();
      if (option == 1) {
        chooseSubjectToEnter(subjects);
      } else if (option == 0)
        return subjects;
    } while (0 != option);
    return subjects;
  }

  private void printAddMoreSubjectOrExit() {
    System.out.println("Czy chcesz dodać więcej przedmiotów?");
    int buttonAddMoreSubject = 1;
    System.out.println("  -> tak wybierz " + buttonAddMoreSubject);
    int buttonExitEnterSubject = 0;
    System.out.println("  -> nie wybierz " + buttonExitEnterSubject);

  }

  private void chooseSubjectToEnter(Set<Subject> subjects) {
    consolePrinter.printLine("Wybierz numer odpowiadający przedmiotowi wyświetlonemu na ekranie");
    Subject.printSubject();
    switch (getInt()) {
      case (1) -> subjects.add(Subject.MATH);
      case (2) -> subjects.add(Subject.POLISH_LANGUAGE);
      case (3) -> subjects.add(Subject.ENGLISH_LANGUAGE);
    }
  }

  public int getInt() {
    try {
      return scanner.nextInt();
    } finally {
      scanner.nextLine();
    }
  }


  private String getString() {
    return scanner.nextLine();
  }

//  public void printSubject() {
//      for (Subject value : Subject.values()) {
//        System.out.println(value);
//      }
}


//  enum Subject {
//    MATH("MAT", "Matematyka"),
//    POLISH_LANGUAGE("POL", "Język Poolski"),
//    ENGLISH_LANGUAGE("ENG", "Język Angielski");
//
//    private final String code;
//    private final String fullName;
//
//    Subject(final String code, final String fullName) {
//      this.code = code;
//      this.fullName = fullName;
//    }
//
//    public String getCode() {
//      return code;
//    }
//
//    public String getFullName() {
//      return fullName;
//    }
//
//    public  static Subject fromFullName(String subject) {
//      Subject[] values = values();
//      for (Subject subject1 : values) {
//        if (subject1.getFullName().equals(subject))
//          return subject1;
//      }
//      return null;
//    }



