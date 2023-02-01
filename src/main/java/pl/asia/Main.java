package pl.asia;

import pl.asia.dao.TeacherDao;
import pl.asia.service.TeacherService;

public class Main {

  public static void main(String[] args) {
    TeacherDao teacherDao = new TeacherDao();
    TeacherService teacherService = new TeacherService(teacherDao);
    ControLoop controLoop = new ControLoop(teacherDao, teacherService);
  }
}
