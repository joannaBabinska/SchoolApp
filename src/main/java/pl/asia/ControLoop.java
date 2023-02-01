package pl.asia;

import pl.asia.dao.TeacherDao;
import pl.asia.service.TeacherService;

public class ControLoop {

  private final TeacherDao teacherDao;
  private final TeacherService teacherService;

  public ControLoop(TeacherDao teacherDao, TeacherService teacherService) {
    this.teacherDao = teacherDao;
    this.teacherService = teacherService;
  }

  void loop() {
    // opttion: dodaj nowego naucycuela
//    teacherService.addTeacher();
  }
}
