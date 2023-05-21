package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the member class, its overall function is to set up a Member's profile
 * on the Weather Top application by looking for information such as users
 * name, email and password.
 * This class is also responsible for double checking if the email is currently
 * registered to the database and also checks if the password that the user has entered
 * is correct.
 *
 * @author Aidas Vaiciunas
 * @version 1.0
 */
@Entity
public class Member extends Model {

  // private instance fields
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  /**
   * Two constructors - to initialise Stations and Readings into arraylists
   * this is to provide functionality for different members having separate
   * stations and readings.
   */
  @OneToMany(cascade = CascadeType.ALL)
  public List<Station> stationList = new ArrayList<Station>();
  @OneToMany
  public List<Reading> readingList = new ArrayList<Reading>();

  /**
   * This is a 'member' constructor that takes four parameters     *
   *
   * @param firstname = name of the user
   * @param lastname  = surname of the user
   * @param email     = email of the user
   * @param password  = password of the user
   */
  public Member(String firstname, String lastname, String email, String password) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
  }

  /**
   * This is a method in a Java class that allows you to find a member object by email.
   * The method takes in an email parameter and uses it to perform a database query to search
   * for a member object that matches the email.
   *
   * @param email = email of the member
   * @return if found: Member object that was retrieved from the database otherwise: 'null'
   */
  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  /**
   * This is a method in a Java class that checks whether a given password matches the password
   * associated with an object instance of that class i.e. Email.
   *
   * @param password = password of the user
   * @return This statement returns true if the given password matches or false otherwise.
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  /**
   * Getters for the class
   */
  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  /**
   * Setters for the class
   */
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
