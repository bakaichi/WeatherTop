package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import utilities.Conversion;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

/**
 * This class can be used to store and manipulate weather data for
 * a particular station, and can be used in conjunction with
 * the Reading class and the Conversion utility class to perform
 * conversions on weather data and to manipulate station data.
 * This class also helps with calculations to Display different icons
 * for weather readings.
 *
 * @author Aidas Vaiciunas
 * @version 2.0
 */
@Entity
public class Station extends Model {
    /**
     * Fields for Station
     */
    private String name;
    private float lat, lng;
    @OneToMany(cascade = CascadeType.ALL)

    // Readings constructed in an ArrayList
    public List<Reading> readings = new ArrayList<Reading>();


    /**
     * Constructor for Station - Taking only one parameter of 'name'
     */
    public Station(String name) {
        this.name = name;
    }

    /**
     * Overloaded constructor for Station taking 3 parameters
     * @param name = name of the station
     * @param lat = latitude of the station
     * @param lng = longitude of the station
     */
    public Station(String name, float lat, float lng){
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * This method is responsible for retrieving the latest code added to
     * the database
     *
     * @return latest code from the ArrayList
     */
    public int getLatestCode(){
        if (readings.isEmpty()){  // if readings are empty return 0
            return 0;
        } else {  // go through the array of readings and get latest code entered for each station
            return readings.get(readings.size() -1).getCode();
        }
    }

    /**
     * This method works in conjuction with Conversion class to work out the correct Weather icon
     * to use.
     * @return String that will display appropriate icon via Bulma Syntax
     */
    public String toCodeIcon(){
        return Conversion.convertToWeatherIcon(getLatestCode());
    }

    /**
     * This method is responsible to convert the Latest code into a string value
     * the conversion itself is linked to "Conversion" class
     * @return String "thunder, rain" etc.
     */
    public String toCode(){
        return Conversion.convertWeatherCode(getLatestCode());
    }

    /**
     * This method is responsible for retrieving the latest Temperature added to
     * the database
     * @return latest Temperature in the ArrayList
     */
    //get latest temperature
    public double getLatestTemp(){
        if (readings.isEmpty()){
            return 0;
        } else {
            return readings.get(readings.size() -1).getTemperature();
        }
    }

    /**
     * This method works in conjuction with Conversion class to work out the correct Temperature icon
     * to use.
     * @return String that will display appropriate icon via Bulma Syntax
     */
    public String toTempIcon(){
        return Conversion.convertToTempIcon(getLatestTemp());
    }

    /**
     * This method converts the latest Temperature in Celcius into Fahrenheit value
     * @return double Temperature in Fahrenheit
     */
    public double toFahrenheit(){
        return Conversion.convertToFahrenheit(getLatestTemp());
    }

    /**
     * This method retrieves the latest added WindSpeed from the Database/ArrayList
     * @return double: Latest Wind Speed
     */
    public double getLatestWindSpeed(){
        if (readings.isEmpty()){
            return 0;
        } else {
            return readings.get(readings.size() -1).getWindSpeed();
        }
    }


    /**
     * This method gets the latest Wind Direction from the database/ArrayList
     *
     * @return int: Latest added Wind Direction
     */
    public int getLatestWindDirection(){
        if(readings.isEmpty()){
            return 0;
        } else {
            return readings.get(readings.size() -1).getWindDirection();
        }
    }

    /**
     * This method takes latest wind direction and converts it into String value
     *
     * @return String: Numbers converted into Strings (North, East etc.)
     */
    public String windDirection(){
        return Conversion.windDirectionToString(getLatestWindDirection());
    }

    /**
     * This method calculates Wind Chill by using Wind Speed and Temperature in
     * its calculation specified in "Conversion" class
     *
     * @return double: Wind chill factor.
     */
    public double toWindChill(){
        return Conversion.toWindChill(getLatestWindSpeed(), getLatestTemp());
    }

    /**
     * This method converts Windpseed into Beufort scale and also converts double
     * value into an int (i.e. no decimal point result)
     *
     * @return int: In Beufort scale format
     */
    public int toBeufort(){
        int windSpeed = (int) getLatestWindSpeed(); // convert the double to int
        return Conversion.convertToBeufort(windSpeed); // convert the int to beufort
    }

    /**
     * This method gets latest Pressure from the ArrayList/Database
     *
     * @return int: Last added Pressure to the ArrayList
     */
    public int getLatestPressure(){
        if(readings.isEmpty()){
            return 0;
        } else{
            return readings.get(readings.size() -1).getPressure();
        }
    }

    /**
     * This method loops through each element of an array and finds the maximum value
     * stored within.
     * @return max value of wind speed
     */
    public double getMaxWindSpeed(){
       double maxWindSpeed = Double.MIN_VALUE;
       for (Reading reading : readings){
           if (reading.getWindSpeed() > maxWindSpeed) {
               maxWindSpeed = reading.getWindSpeed();
           }
       }
       return maxWindSpeed;
    }

    /**
     * This method loops through each element of an array and finds the minimum value
     * stored within.
     * @return minimum value of wind speed
     */
    public double getMinWindSpeed(){
        double minWindSpeed = Double.MAX_VALUE;
        for (Reading reading : readings){
            if (reading.getWindSpeed() < minWindSpeed){
                minWindSpeed = reading.getWindSpeed();
            }
        }
        return minWindSpeed;
    }

    /**
     * This method loops through each element of an array and finds the maximum value
     * stored within.
     * @return max value of Pressure
     */
    public int getMaxPressure(){
        int maxPressure = Integer.MIN_VALUE;
        for (Reading reading : readings){
            if (reading.getPressure() > maxPressure){
                maxPressure = reading.getPressure();
            }
        }
        return maxPressure;
    }

    /**
     * This method loops through each element of an array and finds the minimum value
     * stored within.
     * @return minimum value of Pressure
     */
    public int getMinPressure(){
        int minPressure = Integer.MAX_VALUE;
        for (Reading reading : readings){
            if (reading.getPressure() < minPressure){
                minPressure = reading.getPressure();
            }
        }
        return minPressure;
    }

    /**
     * This method loops through each element of an array and finds the maximum value
     * stored within.
     * @return max value of Temperature
     */
    public double getMaxTemp(){
        double maxTemp = Double.MIN_VALUE;
        for (Reading reading : readings){
            if (reading.getTemperature() > maxTemp){
                maxTemp = reading.getTemperature();
            }
        }
        return maxTemp;
    }

    /**
     * This method loops through each element of an array and finds the minimum value
     * stored within.
     * @return minimum value of Temperature
     */
    public double getMinTemp(){
        double minTemp = Double.MAX_VALUE;
        for (Reading reading : readings){
            if (reading.getTemperature() < minTemp){
                minTemp = reading.getTemperature();
            }
        }
        return minTemp;
    }

    // getters

    public String getName() {
        return name;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}