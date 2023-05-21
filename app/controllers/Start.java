package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * This class is responsible for rendering the home page of the application
 * @author Aidas Vaiciunas
 * @version 1.0
 */
public class Start extends Controller {
  public static void index() {
    Logger.info("Rendering Main-Page");
    render ("start.html");
  }
}
