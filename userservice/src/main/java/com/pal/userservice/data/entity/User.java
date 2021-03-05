package com.pal.userservice.data.entity;

import com.github.palmurugan.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

  @Column(name = "username", nullable = false, unique = true)
  private String userName;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "firstname", nullable = false)
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "status", nullable = false)
  private String status;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "User{" +
        "userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", status='" + status + '\'' +
        '}';
  }
}
