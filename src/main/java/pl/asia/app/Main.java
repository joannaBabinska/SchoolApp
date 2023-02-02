package pl.asia.app;

import pl.asia.dao.TeacherDao;
import pl.asia.io.file.ConsolePrinter;
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
    ControlLoop controLoop = new ControlLoop(teacherDao, teacherService,consolePrinter);
     Set<String> JoannaSubject = new HashSet<>();
     JoannaSubject.add("Biologia");

    Teacher teacher1 = new Teacher("Joanna", "Babińska", LocalDate.of(1999,10,12), JoannaSubject, new BigDecimal(100));
     teacherDao.save(teacher1);
     teacherDao.saveAllSubject(teacher1);


  }

}
