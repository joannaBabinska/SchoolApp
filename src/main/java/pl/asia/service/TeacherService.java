package pl.asia.service;

import pl.asia.dao.TeacherDao;
import pl.asia.model.Teacher;

import java.util.List;
import java.util.Optional;

public class TeacherService {

  private final TeacherDao teacherDao = new TeacherDao();



  public String findTeacherByName(String fullName) {
    return teacherDao.findTeacherByName(fullName);
  }

  public Optional<Teacher> findSubjectForTeacher(String allBaseInformationAboutTeacher) {
    return teacherDao.findSubjectForTeacher(allBaseInformationAboutTeacher);
  }

  public List<String> takeAllTeachersNamesFromDatabase() {
    return teacherDao.takeAllTeachersNamesFromDatabase();
  }

  public void saveToDatabase(Teacher teacher) {
    teacherDao.saveTeacherToDatabase(teacher);
    teacherDao.saveSubjectToDatabase(teacher);
  }


}
