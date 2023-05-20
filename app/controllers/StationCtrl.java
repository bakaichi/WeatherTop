package controllers;

import models.Member;
import models.Reading;
import models.Station;


import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {

    public static void index(Long id){
        Station station = Station.findById(id);
        Logger.info("Rendering Station id = " + id);
        render("station.html", station);
    }

    public static void addReading(Long id, int code, double temperature, double windSpeed, int windDirection, int pressure){
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingid){
        Logger.info("Deleting reading " + readingid);
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);
    }
}
