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

  private void AddMoreSubjectOrExitLoop(Set<Subject> subjects) {
    int option;
    do {
      printAddMoreSubjectOrExit();
      option = getInt();
      if (option == 1) {
        chooseSubjectToEnter(subjects);
      } else if (option == 0) {
        break;
      }
    } while (0 != option);
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
      case (4) -> subjects.add(Subject.INFORMS);
      case (5) -> subjects.add(Subject.GEOGRAPHY);
      case (6) -> subjects.add(Subject.BIOLOGY);
      case (7) -> subjects.add(Subject.CHEMISTRY);
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

}


