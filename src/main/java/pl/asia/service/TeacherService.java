package pl.asia.service;

import pl.asia.dao.TeacherDao;
import pl.asia.model.Teacher;

import java.util.List;
import java.util.Optional;

public class TeacherService {

  private final TeacherDao teacherDao;

  public TeacherService(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }


  public Optional<Teacher> findTeacherByName(String fullName) {
    return teacherDao.findByName(fullName);
  }

  public List<String> takeAllTeachersNamesFromDatabase() {
    return teacherDao.takeAllTeachersNamesFromDatabase();
  }

  public void saveToDatabase(Teacher teacher) {
    teacherDao.save(teacher);

  }


  public void deleteTeacher(String name) {
    teacherDao.delete(name);
  }
}
