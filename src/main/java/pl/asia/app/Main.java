package pl.asia.app;

import pl.asia.dao.TeacherDao;
import pl.asia.io.ConsolePrinter;
import pl.asia.io.DataReader;
import pl.asia.model.Subject;
import pl.asia.service.TeacherService;

public class Main {

  public static void main(String[] args) {
    TeacherDao teacherDao = new TeacherDao();
    TeacherService teacherService = new TeacherService(teacherDao);
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataReader dataReader = new DataReader(consolePrinter);
    Subject.printSubjectForChooseLoop();

  }

}
