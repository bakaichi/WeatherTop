package controllers;

import models.Member;
import models.Reading;
import models.Station;


import play.Logger;
import play.mvc.Controller;

/**
 * This is a controller class used for manipulating and rendering data within
 * each station such as adding and removing readings.
 *
 * @author Aidas Vaiciunas
 * @version 2.0
 */
public class StationCtrl extends Controller {

  /**
   * This method renders a specific station when folder button is clicked.
   * It works in conjunction with station.html partial and routes file.
   *
   * @param id of the station
   */
  public static void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Rendering Station id = " + id);
    render("station.html", station);
  }

  /**
   * This method is used for adding new readings to the database of the current
   * station the user has opened.
   *
   * @param id            of the station
   * @param code          new reading code
   * @param temperature   new reading temperature
   * @param windSpeed     new reading of wind speed
   * @param windDirection new reading of wind direction
   * @param pressure      new reading of pressure
   */
  public static void addReading(Long id, int code, double temperature, double windSpeed, int windDirection, int pressure) {
    Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/stations/" + id);
  }

  /**
   * This method is responsible for deleting unwanted readings and is called
   * in station.html partial.
   *
   * @param id        of the station the user is inside
   * @param readingid id of the reading user wants to delete
   */
  public static void deleteReading(Long id, Long readingid) {
    Logger.info("Deleting reading " + readingid);
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    redirect("/stations/" + id);
  }
}
