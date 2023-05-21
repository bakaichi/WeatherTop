package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * The Accounts class contains methods related to user account management
 * such as signing up, logging in, logging out, and authentication
 * using the Member model class.
 * The class provides methods for registering a new user
 * authenticating a user's credentials, and retrieving the currently
 *
 * @Author: Aidas Vaiciunas
 * @Version: 2.0
 */
public class Accounts extends Controller {
  /**
   * This method does not take any parameters
   * purpose of this method is to render the partial named 'signup.html'
   */
  public static void signup() {
    render("signup.html");
  }

  /**
   * This method does not take any parameters
   * purpose of this method is to render the partial named 'login.html'
   */
  public static void login() {
    render("login.html");
  }

  /**
   * Logs out the current user by clearing their session data and redirecting
   * them to the home page of the application
   * - no params
   * - no return value
   */
  public static void logout() {
    session.clear();
    redirect("/");
  }

  /**
   * This method Registers a new user with the specified information, saves their information
   * to the database, and redirects the user to the home page of the application.
   * This method takes four parameters, which are the user's first name, last name,
   * email address, and password.
   * his method creates a new Member object using the provided information, saves it to the database
   * using the save() method, and redirects the user to the home page of the
   * <p>
   * - no return value
   */
  public static void register(String firstname, String lastname, String email, String password) {
    Logger.info("Registering new user" + email);
    Member member = new Member(firstname, lastname, email, password);
    member.save();
    redirect("/");
  }

  /**
   * This method authenticates a user with the specified email and password by checking if the email and password
   * match a user in the database.
   * If the password and email match the user gets redirected to 'dashboard'
   * Otherwise the user will be redirected back to 'login' page.
   *
   * @param email    users email
   * @param password users password
   *                 - no return values
   */
  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate user with " + email + " + " + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password))) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  /**
   * This method returns a Member object representing the currently logged-in user
   * or null if no user is currently logged in.
   * If this method gets the member ID during the session otherwise redirects the user
   * back to the login page.
   *
   * @return logged in members id
   */
  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }
}
