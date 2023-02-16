package pl.asia.io;

import pl.asia.exception.NoSuchOptionException;
import pl.asia.model.Lesson;
import pl.asia.model.Student;
import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DataReader {
  private final Scanner scanner = new Scanner(System.in);
  private final ConsolePrinter consolePrinter;

  public DataReader(ConsolePrinter consolePrinter) {
    this.consolePrinter = consolePrinter;
  }

  public Teacher enterTeacher() {
    ConsolePrinter.printLine("Wprowadź dane nowego nauczyciela");
    ConsolePrinter.printLine("Podaj imię");
    String firstName = getString();
    ConsolePrinter.printLine("Podaj nazwisko");
    String lastName = getString();
    ConsolePrinter.printLine("Podaj datę urodzenia w formacie YYYY-MM-DD");// obsłuż wyjątek
    String dateOfBirth = getString();
    LocalDate localDateDateOfBirth = LocalDate.parse(dateOfBirth);
    System.out.println("Jakiego przedmiotu może uczyć?");
    Set<Subject> subjects = enterSubjects();
    ConsolePrinter.printLine("Podaj stawkę godzinową");
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
        chooseSubjectToEnter(subjects);}
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
    ConsolePrinter.printLine("Wybierz numer odpowiadający przedmiotowi wyświetlonemu na ekranie");
    Subject.printSubjectForChooseLoop();
    subjects.add(Subject.makeSubjectFromIntNumber(getInt()));

  }

//  private Subject chooseOneSubject() {
//    Subject.printSubjectForChooseLoop();
//    return switch (getInt()) {
//      case (1) -> Subject.MATH;
//      case (2) -> Subject.POLISH_LANGUAGE;
//      case (3) -> Subject.ENGLISH_LANGUAGE;
//      case (4) -> Subject.INFORMS;
//      case (5) -> Subject.GEOGRAPHY;
//      case (6) -> Subject.BIOLOGY;
//      case (7) -> Subject.CHEMISTRY;
//      default -> throw new NoSuchOptionException("Brak wybranego numeru");
//
//    };
//
//  }

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

  public Student enterStudent() {
    ConsolePrinter.printLine("Wprowadź dane nowego ucznia");
    ConsolePrinter.printLine("Podaj imię");
    String firstName = getString();
    ConsolePrinter.printLine("Podaj nazwisko");
    String lastName = getString();
    ConsolePrinter.printLine("Podaj datę urodzenia w formacie YYYY-MM-DD");// obsłuż wyjątek
    String dateOfBirth = getString();
    LocalDate localDateDateOfBirth = LocalDate.parse(dateOfBirth);
    ConsolePrinter.printLine("Podaj numer klasy do  której chodzi");
    int grade = getInt();
    ConsolePrinter.printLine("Wybierz szkołę do której chodzi");
    String school = chooseSchool();
    return new Student(firstName, lastName, localDateDateOfBirth, grade, school);
  }

  private String chooseSchool() {
    printSchoolList();
    String selectedSchool = null;
    switch (getInt()) {
      case 1 -> selectedSchool = "Szkoła podstawowa";
      case 2 -> selectedSchool = "Liceum";
      case 3 -> selectedSchool = "Technikum";
    }
    return selectedSchool;
  }

  private void printSchoolList() {
    ConsolePrinter.printLine("""
            Lista szkół:
             Szkoła podstawowa -> wybierz 1
             liceum            -> wybierz 2
             technikum         -> wybierz 3""");
  }

  public String getName() {
    ConsolePrinter.printLine("Podaj imię");
    String firstName = getString();
    ConsolePrinter.printLine("Podaj nazwisko");
    String lastName = getString();
    return firstName + " " + lastName;
  }

  public Lesson enterLesson() {
    return null;
  }
}

//  public Lesson enterLesson(){
//    String id = chooseOneSubject().getCode();
//    consolePrinter.printLine("Podaj glówną tematyke/ cel lekcji");
//    String topic = getString();
//    consolePrinter.printLine("Kiedy mają się odwywać zajęcia - podaj datę w formacie YYYY-MM-DD");
//    LocalDate dataOfLesson = LocalDate.parse(getString());
//    consolePrinter.printLine("Podaj czas trwania zajęć");
//    int duration = getInt();
//    consolePrinter.printLine("Podaj cenę za zajęcia dla 1 osoby");
//    int price = getInt();
//    consolePrinter.printLine("Podaj numer pokoju zajęć");
//    int numberOfRoom = getInt();
//    consolePrinter.printLine("Podaj dane nauczyciela prowadzącego");
//    String fullTeacherName = getName();








