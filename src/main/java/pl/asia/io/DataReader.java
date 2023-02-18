package pl.asia.io;

import pl.asia.model.Lesson;
import pl.asia.model.Student;
import pl.asia.model.Subject;
import pl.asia.model.Teacher;
import pl.asia.service.TeacherService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


  public int getInt() {
    try {
      return scanner.nextInt();
    } finally {
      scanner.nextLine();
    }
  }

  public String getString() {
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


  public String getName() {
    ConsolePrinter.printLine("Podaj imię");
    String firstName = getString();
    ConsolePrinter.printLine("Podaj nazwisko");
    String lastName = getString();
    return firstName + " " + lastName;
  }



  public Lesson enterLesson(TeacherService teacherService) {
    ConsolePrinter.printLine("Wybierz przedmiot reprezentujący lekcję");
    Subject.printSubjectForChooseLoop();
    String name = Subject.makeSubjectFromIntNumber(getInt()).getCode();
    ConsolePrinter.printLine("Wybierz prowadzącego zajęć wpisują odpowiedni numer");

    teacherService.namesAndIdList().forEach(ConsolePrinter::printLine);
    int teacherId = getInt();
    ConsolePrinter.printLine("Podaj glówną tematyke/ cel lekcji");
    String topic = getString();
    ConsolePrinter.printLine("Kiedy mają się odbywać zajęcia - podaj datę w formacie YYYY-MM-DDTHH:MM");
    LocalDateTime dataOfLesson = LocalDateTime.parse(getString());
    ConsolePrinter.printLine("Podaj czas trwania zajęć");
    int duration = getInt();
    ConsolePrinter.printLine("Podaj cenę za zajęcia dla 1 osoby");
    int price = getInt();
    ConsolePrinter.printLine("Podaj numer pokoju zajęć");
    int numberOfRoom = getInt();
    ConsolePrinter.printLine("Wybierz numer odpowiadający nauczycielowi prowadzocemu zajęcia");

    return new Lesson(name,teacherId,topic,dataOfLesson,duration,numberOfRoom,price);
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

  }}





