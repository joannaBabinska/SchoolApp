package pl.asia.service;

import pl.asia.dao.TeacherDao;
import pl.asia.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
  public List<String> namesAndIdList(){
    List<Integer> ids = teacherDao.takeAllTeachersNamesFromDatabase()
            .stream()
            .map(teacherDao::findIdByName)
            .toList();
    List<String> names = teacherDao.takeAllTeachersNamesFromDatabase();

    List <String> idAndNamesList = new ArrayList<>();
    for (int i = 0; i < ids.size(); i++) {
      idAndNamesList.add(ids.get(i) + " " + names.get(i));
    }
    return idAndNamesList;
  }


  public void deleteTeacher(String name) {
    int id = teacherDao.findIdByName(name);
    teacherDao.delete(id);
  }
}
