package pl.asia.dao;

import java.util.Optional;

public interface FindingDao <T>{

  Optional <T> findByName(String name);


}
