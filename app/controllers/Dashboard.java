package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Reading;
import models.Station;
import models.Member;

import play.Logger;
import play.mvc.Controller;



public class Dashboard extends Controller{

  public static void index(){
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stationList = member.stationList;
    List<Reading> readingList = member.readingList;
    render("dashboard.html", member, stationList, readingList);
  }

  public static void addStation(String title, float lat, float lng){
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(title, lat, lng);
    member.stationList.add(station);
    member.save();
    Logger.info("Adding a new Station " + title);
    redirect("/dashboard");
  }

}

