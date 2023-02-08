package pl.asia.app;

import pl.asia.dao.TeacherDao;
import pl.asia.io.file.ConsolePrinter;
import pl.asia.io.file.DataReader;
import pl.asia.model.Subject;
import pl.asia.model.Teacher;
import pl.asia.service.TeacherService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    TeacherDao teacherDao = new TeacherDao();
    TeacherService teacherService = new TeacherService(teacherDao);
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataReader dataReader = new DataReader(consolePrinter);
    Subject.printSubject();

  }

}
