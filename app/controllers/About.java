package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 * This is a controller class responsible for rendering the about page.
 *
 * @author Aidas Vaiciunas
 * @version 1.0
 */
public class About extends Controller {
  public static void index() {
    Logger.info("Rendering about");
    render("about.html");
  }
}

