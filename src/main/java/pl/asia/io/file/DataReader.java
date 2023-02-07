package pl.asia.io.file;

import pl.asia.model.Subject;
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
    consolePrinter.printLine("Wybierz przedmiot wśród wyświetlonych");
    Subject.printSubject(); // dodaj lun exit przez switcha
    Set<Subject> subjects = readSubjects(numberOfSchoolSubject);
    consolePrinter.printLine("Podaj stawkę godzinową");
    int hourlyWage = scanner.nextInt();
    return new Teacher(firstName, lastName, localDateDateOfBirth, subjects, BigDecimal.valueOf(hourlyWage));
  }

  private Set<Subject> readSubjects(int numberOfSchoolSubject) {
    Set<Subject> subjects = new HashSet<>();
    do {
      Subject subject = readSubject();
      subjects.add(subject);
    } while (!(subjects.size() == numberOfSchoolSubject));
    return subjects;
  }

  private Subject readSubject() {
    consolePrinter.printLine("Podaj nazwę jednego przedmiotu");
    String subjectName = scanner.nextLine();
    Subject subject = Subject.fromFullName(subjectName);
    return subject;
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



