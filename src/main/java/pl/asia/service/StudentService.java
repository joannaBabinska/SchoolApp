package pl.asia.service;

import pl.asia.dao.StudentDao;
import pl.asia.model.Student;

public class StudentService {

  private final StudentDao studentDao = new StudentDao();


  public void add(Student student) {
    studentDao.saveStudentToDatabase(student);
  }

  public void findStudentId(String fullName) {
    studentDao.findStudentIdByFullName(fullName);
  }
}

