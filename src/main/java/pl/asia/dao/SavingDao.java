package pl.asia.dao;


public interface SavingDao <T> {

  T save (T entity);
}
