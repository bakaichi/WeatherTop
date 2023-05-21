package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Reading;
import models.Station;
import models.Member;

import play.Logger;
import play.mvc.Controller;


/**
 * This is a Dashboard controller class responsible for rendering and validating
 * members logging in and adding/deleting stations.
 *
 * @author Aidas Vaiciunas
 * @version 2.0
 */
public class Dashboard extends Controller {

  /**
   * This method is responsible for logging in a member to their own personal view
   * of Stations and Readings that will not be present for other members. It also renders
   * the dashboard.html page for the logged in member.
   */
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stationList = member.stationList;
    List<Reading> readingList = member.readingList;
    render("dashboard.html", member, stationList, readingList);
  }

  /**
   * This method is responsible for adding a new station to the Dashboard. It works
   * in conjunction with the dashboard.html partial and the routes file.
   *
   * @param title title of the station user wants to add
   * @param lat   latitude of the station being added
   * @param lng   longitude of the station being added
   */
  public static void addStation(String title, float lat, float lng) {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(title, lat, lng);
    member.stationList.add(station);
    member.save();
    Logger.info("Adding a new Station " + title);
    redirect("/dashboard");
  }

  /**
   * This method is responsible for removing/deleting a station from the database
   * it also works together with dashboard partial and routes file.
   *
   * @param id        of the member
   * @param stationid id of the station user wants to delete
   */
  public static void deleteStation(Long id, Long stationid) {
    Member member = Member.findById(id);
    Station station = Station.findById(stationid);
    member.stationList.remove(station);
    member.save();
    station.delete();
    Logger.info("Deleting " + station.getName());
    redirect("/dashboard");
  }
}

