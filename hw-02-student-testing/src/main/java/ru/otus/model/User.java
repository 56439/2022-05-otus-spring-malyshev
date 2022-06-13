package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private String name;
  private String surname;

  public User(String[] userData) {
    this.name = userData[0];

    if (userData.length > 1) {
      this.surname = userData[1];
    }
  }
}