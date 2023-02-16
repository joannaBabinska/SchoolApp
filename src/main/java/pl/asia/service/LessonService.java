package pl.asia.service;

import pl.asia.dao.LessonDao;
import pl.asia.dao.SavingDao;
import pl.asia.model.Lesson;

public class LessonService implements SavingDao<Lesson> {

    private final LessonDao lessonDao;


  public LessonService(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public Lesson save(Lesson entity) {
    lessonDao.save();
  }
}
