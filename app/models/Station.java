package models;

import java.util.ArrayList;
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
 * conversions on weather data
 *
 * @Author: Aidas Vaiciunas
 * @Version: 1.0
 */
@Entity
public class Station extends Model {
    /**
     * Fields for Station
     */
    public String name;
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
     * This method is responsible for retrieving the latest code added to
     * the database
     *
     * @returns Latest code from the ArrayList
     */
    public int getLatestCode(){
        if (readings.isEmpty()){  // if readings are empty return 0
            return 0;
        } else {  // go through the array of readings and get latest code entered for each station
            return readings.get(readings.size() -1).getCode();
        }
    }

    /**
     * This method is responsible to convert the Latest code into a string value
     * the conversion itself is linked to "Conversion" class
     *
     * @returns String "thunder, rain" etc.
     */
    public String toCode(){
        return Conversion.convertWeatherCode(getLatestCode());
    }

    /**
     * This method is responsible for retrieving the latest Temperature added to
     * the database
     *
     * @returns latest Temperature in the ArrayList
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
     * This method converts the latest Temperature in Celcius into Fahrenheit value
     *
     * @returns double : Temperature in Fahrenheit
     */
    public double toFahrenheit(){
        return Conversion.convertToFahrenheit(getLatestTemp());
    }

    /**
     * This method retrieves the latest added WindSpeed from the Database/ArrayList
     *
     * @returns double: Latest Wind Speed
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
     * @returns int: Latest added Wind Direction
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
     * @returns String: Numbers converted into Strings (North, East etc.)
     */
    public String windDirection(){
        return Conversion.windDirectionToString(getLatestWindDirection());
    }

    /**
     * This method calculates Wind Chill by using Wind Speed and Temperature in
     * its calculation specified in "Conversion" class
     *
     * @returns double: Wind chill factor.
     */
    public double toWindChill(){
        return Conversion.toWindChill(getLatestWindSpeed(), getLatestTemp());
    }

    /**
     * This method converts Windpseed into Beufort scale and also converts double
     * value into an int (i.e. no decimal point result)
     *
     * @returns int: In Beufort scale format
     */
    public int toBeufort(){
        int windSpeed = (int) getLatestWindSpeed(); // convert the double to int
        return Conversion.convertToBeufort(windSpeed); // convert the int to beufort
    }


    /**
     * This method gets latest Pressure from the ArrayList/Database
     *
     * @returns int: Last added Pressure to the ArrayList
     */
    public int getLatestPressure(){
        if(readings.isEmpty()){
            return 0;
        } else{
            return readings.get(readings.size() -1).getPressure();
        }
    }

}