package pl.asia.app;
import pl.asia.dao.StudentDao;
import pl.asia.dao.TeacherDao;
import pl.asia.exception.NoSuchOptionException;
import pl.asia.io.ConsolePrinter;
import pl.asia.io.DataReader;
import pl.asia.model.Student;
import pl.asia.model.Teacher;
import pl.asia.service.StudentService;
import pl.asia.service.TeacherService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class ControlLoop {

  TeacherDao teacherDao = new TeacherDao();
  StudentDao studentDao = new StudentDao();
  TeacherService teacherService = new TeacherService(teacherDao);
  StudentService studentService = new StudentService(studentDao);

  ConsolePrinter consolePrinter = new ConsolePrinter();
  DataReader dataReader = new DataReader(consolePrinter);


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
      case DELETE_TEACHER -> deleteTeacher();
      case ADD_STUDENT -> addStudent();
      case ENROLL_A_STUDENT_IN_A_LESSONS -> enrollAStudentInALessons();
      default -> ConsolePrinter.printLine("Nie ma takiej opcji");
    }
  } while (option != Options.EXIT);
  }

  private void deleteTeacher() {
    String name = dataReader.getName();
    teacherService.deleteTeacher(name);
  }

  private void enrollAStudentInALessons() {
    String fullName = dataReader.getName();
    studentService.findStudentId(fullName);
  }

  private void addStudent() {
    Student student = dataReader.enterStudent();
    studentService.add(student);
  }

  private void printInformationAboutTeacher() {
    String fullName = dataReader.getName();
    Optional<Teacher> subjectForTeacher = teacherService.findTeacherByName(fullName);
    subjectForTeacher.ifPresentOrElse(consolePrinter::printAllInformationAboutTeacher,
            () -> ConsolePrinter.printLine("Brak osoby w bazie danych"));
    };


  private void printAllTeachers() {
    List<String> names = teacherService.takeAllTeachersNamesFromDatabase();
    consolePrinter.printAllNameOfTeachers(names);


  }

  private void addTeacher() {
    Teacher teacher = dataReader.enterTeacher();
    teacherService.saveToDatabase(teacher);
    ConsolePrinter.printLine("Dane zapisano poprawnie");
  }

  private void exit(){
    ConsolePrinter.printLine("Do widzenia");
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
    ConsolePrinter.printLine("Wybierz opcję");
    for (Options options : Options.values())
      ConsolePrinter.printLine(options.toString());
  }




  private enum Options {
    EXIT(0, "Wyjście z programu"),
    ADD_TEACHER(1, "Dodaj nauczyciela"),
    PRINT_ALL_TEACHERS(2, "Wyświetl wszystkich nauczycieli"), 
    PRINT_INFORMATION_ABOUT_TEACHER(3, "Wyświetl informacje o nauczycielu"),
    DELETE_TEACHER(4,"Usuń nauczyciela z bazy danych"),
    ADD_STUDENT(4,"Dodaj ucznia"),
    ENROLL_A_STUDENT_IN_A_LESSONS(5, "Zapisz ucznia na zajęcia");

    private final String descriptions;
    private final int value;

    Options(int optionsNumber, String descriptions) {
      this.descriptions = descriptions;
      this.value = optionsNumber;
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
