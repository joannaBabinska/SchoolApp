package pl.asia.io;

import pl.asia.model.Subject;
import pl.asia.model.Teacher;

import java.util.List;

public class ConsolePrinter {

  public static void printLine(String text) {
    System.out.println(text);
  }

  public void printError(String text) {
    System.err.println(text);
  }

  public void printInformationAboutTeacher(Teacher teacher) {
    printLine("Imię:" + teacher.getFirstName() + "; Nazwisko: " + teacher.getLastName() + "; Data urodzenia: "
            + teacher.getDateOfBirth() + "; Wynagordzenie za godzine: " + teacher.getHourlyWage());

  }

  public void printSubjectForTeacher(Teacher teacher){
    Subject.printSubjectInfo(teacher);
  }

  public void printAllInformationAboutTeacher(Teacher teacher){
    printInformationAboutTeacher(teacher);
    printLine("Przedmioty, któreych może uczyć:");
    printSubjectForTeacher(teacher);
  }

  public void printAllNameOfTeachers(List<String> teachers) {
  teachers.forEach(ConsolePrinter::printLine);
  }

}
