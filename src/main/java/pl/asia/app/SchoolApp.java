package pl.asia.app;

import pl.asia.dao.StudentDao;
import pl.asia.dao.TeacherDao;
import pl.asia.io.file.ConsolePrinter;
import pl.asia.io.file.DataReader;
import pl.asia.service.TeacherService;

public class SchoolApp {
  public static void main(String[] args) {
    TeacherDao teacherDao = new TeacherDao();
    TeacherService teacherService = new TeacherService(teacherDao);
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataReader dataReader = new DataReader(consolePrinter);
    StudentDao studentDao = new StudentDao();

    ControlLoop controlLoop = new ControlLoop(teacherDao,teacherService,consolePrinter,dataReader,studentDao);
    controlLoop.mainLoop();
  }
}
