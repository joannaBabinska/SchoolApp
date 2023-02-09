package pl.asia.app;

import pl.asia.dao.TeacherDao;
import pl.asia.exception.NoSuchOptionException;
import pl.asia.io.file.ConsolePrinter;
import pl.asia.io.file.DataReader;
import pl.asia.model.Teacher;
import pl.asia.service.TeacherService;

import java.util.InputMismatchException;

public class ControlLoop {

  private final TeacherDao teacherDao;
  private final TeacherService teacherService;
  private final ConsolePrinter consolePrinter;
  private final DataReader dataReader;

  public ControlLoop(TeacherDao teacherDao, TeacherService teacherService, ConsolePrinter consolePrinter, DataReader dataReader) {
    this.teacherDao = teacherDao;
    this.teacherService = teacherService;
    this.consolePrinter = consolePrinter;
    this.dataReader = dataReader;
  }

  void mainLoop() {
    Options option = null;
    do{
    printOptions();
    option = getOptions();
    switch (option){
      case EXIT -> exit();
      case ADD_TEACHER -> addTeacher();
      case PRINT_ALL_TEACHERS -> printAllTeachers();
      case PRINT_INFORMATION_ABOUT_TEACHER -> printInformationAboutTeacher();
      default ->consolePrinter.printLine("Nie ma takiej opcji");
    }
  } while (option != Options.EXIT);
  }

  private void printInformationAboutTeacher() {
  }

  private void printAllTeachers() {
    teacherDao.printAllTeachers();
    
  }

  private void addTeacher() {
    Teacher teacher = dataReader.enterTeacher();
    teacherDao.saveTeacherToDatabase(teacher);
    teacherDao.saveSubjectToDatabase(teacher);
    consolePrinter.printLine("Dane zapisano poprawnie");
  }

  private void exit(){
    consolePrinter.printLine("Do widzenia");
  }
  private Options getOptions() {
    boolean optionOk = false;
    Options option = null;
    while (!optionOk) {
      try {
        option = Options.createFromInt(dataReader.getInt());
        optionOk = true;
      } catch (NoSuchOptionException e) {
        consolePrinter.printError(e.getMessage());
      } catch (InputMismatchException e) {
        consolePrinter.printError("Wprowadzona wartość nie jest liczbą, podaj ją ponownie");
      }
    }
    return option;
  }




  private void printOptions() {
    consolePrinter.printLine("Wybierz opcję");
    for (Options options : Options.values())
      consolePrinter.printLine(options.toString());
  }




  private enum Options {
    EXIT(0, "Wyjście z programu"),
    ADD_TEACHER(1, "Dodaj nauczyciela"),
    PRINT_ALL_TEACHERS(2, "Wyświetl wszystkich nauczycieli"), 
    PRINT_INFORMATION_ABOUT_TEACHER(3, "Wyświetl informacje o nauczycielach");

    private final String descriptions;
    private final int value;

    Options(int optionsNumber, String descriptions) {
      this.descriptions = descriptions;
      this.value = optionsNumber;
    }

    public String getDescriptions() {
      return descriptions;
    }

    public int getValue() {
      return value;
    }

    @Override
    public String toString() {
      return value + " - " + descriptions;
    }

    private static Options createFromInt(int options) {
      try {
        return Options.values()[options];
      } catch (ArrayIndexOutOfBoundsException e){
          throw new NoSuchOptionException ("Brak opcji o id " + options);
      }
    }

  }
}
