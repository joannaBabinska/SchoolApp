package pl.asia.service;

import pl.asia.dao.StudentDao;
import pl.asia.model.Student;

public class StudentService {

  private final StudentDao studentDao;

  public StudentService(StudentDao studentDao) {
    this.studentDao = studentDao;
  }


  public Student add(Student student) {
    return studentDao.save(student);
  }}

//  public int findStudentId(String fullName) {
//    if(studentDao.findStudentByFullName(fullName).isPresent()){
//      return studentDao.findStudentByFullName(fullName).get().getId();
//    } else{
//      throw new
//    }
//  }
//}

//TODO