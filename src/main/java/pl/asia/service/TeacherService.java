package pl.asia.service;

import pl.asia.dao.TeacherDao;
import pl.asia.model.Teacher;

public class TeacherService {

  private final TeacherDao teacherDao;

  public TeacherService(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }

  public void addTeacher(Teacher teacher) {
    teacherDao.save(teacher);
    System.out.println("Dodano");
  }
}
