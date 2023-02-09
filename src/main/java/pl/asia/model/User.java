package pl.asia.model;

import java.time.LocalDate;

import java.util.Objects;

public abstract class User {
  private int id;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;

  public User(int id, String firstName, String lastName, LocalDate dateOfBirth) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public User(String firstName, String lastName, LocalDate dateOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(dateOfBirth, user.dateOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastName, dateOfBirth);
  }


}
