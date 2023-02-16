package pl.asia.service;

import pl.asia.dao.StudentDao;
import pl.asia.exception.StudentNotExistException;
import pl.asia.io.ConsolePrinter;
import pl.asia.model.Student;

public class StudentService {

  private final StudentDao studentDao;

  public StudentService(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  public Student add(Student student) {
    return studentDao.save(student);
  }


  public void saveDateAboutStudentAndHisLesson(String fullName, int lessonId) {
    int studentId = findStudentId(fullName);
    enrollInALesson(studentId, lessonId);
  }

  private int findStudentId(String fullName) {
    if (studentDao.findStudentByFullName(fullName).isPresent()) {
      return studentDao.findStudentByFullName(fullName).get().getId();
    } else {
      throw new StudentNotExistException("Brak studenta " + fullName + " w bazie danych");
    }
  }

  private void enrollInALesson(int studentId, int lessonId) {
    studentDao.enrollAStudentInTheLesson(studentId, lessonId);
  }
}
//TODO