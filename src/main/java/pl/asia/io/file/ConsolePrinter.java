package pl.asia.io.file;

import pl.asia.model.Subject;
import pl.asia.model.Teacher;

public class ConsolePrinter {

  public void printLine(String text) {
    System.out.println(text);
  }

  public void printError(String text) {
    System.err.println(text);
  }

  public void printInformationAboutTeacher(Teacher teacher) {
    System.out.println("Imię:" + teacher.getFirstName() + "; Nazwisko: " + teacher.getLastName() + "; Data urodzenia: "
            + teacher.getDateOfBirth() + "; Wynagordzenie za godzine: " + teacher.getHourlyWage());
  }

  public void printSubjectForTeacher(Teacher teacher){
    Subject.printSubjectInfo(teacher);
  }

}
