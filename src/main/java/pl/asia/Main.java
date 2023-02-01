package pl.asia;

import pl.asia.dao.TeacherDao;
import pl.asia.model.Teacher;
import pl.asia.service.TeacherService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    TeacherDao teacherDao = new TeacherDao();
    TeacherService teacherService = new TeacherService(teacherDao);
    ControLoop controLoop = new ControLoop(teacherDao, teacherService);
     Set<String> JoannaSubject = new HashSet<>();
     JoannaSubject.add("Matematyka");
    Teacher teacher1 = new Teacher("Joanna", "Babińska", LocalDate.of(1999,10,12), JoannaSubject, new BigDecimal(100));
     teacherDao.save(teacher1);


  }

}
