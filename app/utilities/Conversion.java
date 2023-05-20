package utilities;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * This class is used entirely for converting and manipulating different data
 * as an example for converting weather codes to words and performing calculations
 * like Celcius to Fahrenheit. This class is intended to be called by others to help
 * with various calculations.
 *
 * @author Aidas Vaiciunas
 * @version 2.0
 */
@Entity
public class Conversion extends Model{

    /**
     * This method converts various weather codes to their corresponding meanings
     * in String format.
     * @param code read in from the database
     * @return String conversion of codes
     */
    public static String convertWeatherCode(int code){
        String s;
        switch (code){
            case 800: s = "Thunder";
                    break;
            case 700: s = "Snow";
                    break;
            case 600: s = "Rain";
                    break;
            case 500: s = "Heavy Showers";
                    break;
            case 400: s = "Light Showers";
                    break;
            case 300: s = "Cloudy";
                    break;
            case 200: s = "Partial Clouds";
                    break;
            case 100: s = "Clear";
                    break;
            default:
                s = "No weather codes were added";
        }
        return s;
    }

    /**
     * This method converts different weather Codes into bulma framework calls
     * for various icons that represent the weather.
     * @param code read in from the database
     * @return correct icon call for bulma framework
     */
    public static String convertToWeatherIcon(int code){
        String s = null;
        switch (code){
            case 800: s = "fa-cloud-bolt";
                break;
            case 700: s = "fa-cloud-meatball";
                break;
            case 600: s = "fa-cloud-rain";
                break;
            case 500: s = "fa-cloud-showers-water";
                break;
            case 400: s = "fa-cloud-showers-heavy";
                break;
            case 300: s = "fa-cloud";
                break;
            case 200: s = "fa-cloud-sun";
                break;
            case 100: s = "fa-sun";
        }
        return s;
    }

    /**
     * This method is used to convert temperature in Celcius into Fahrenheit format via use of the formula
     * below.
     * @param temperature that is loaded in from the database in Station class.
     * @return double in fahrenheit value
     */
    public static double convertToFahrenheit(double temperature){
        return temperature * 9 / 5 + 32;
    }

    /**
     * This method converts the Celcius value from the database into Bulma framework call for a corresponding
     * icon.
     * @param temperature that is loaded from the database.
     * @return String with a corresponding call to the Font Awesome library.
     */
    public static String convertToTempIcon(double temperature){
        String s = null;
        if (temperature >= 35){
            s = "fa-temperature-high";
        } else if (temperature <= 35){
            s = "fa-temperature-half";
        } else if (temperature <= 20) {
            s = "fa-temperature-quarter";
        } else if (temperature <= 10) {
            s = "fa-temperature-low";
        } else {
            s = "fa-temperature-empty";
        }
        return s;
    }

    /**
     * This method converts wind speed into Beufort format using calculations presented in the brief of the
     * assignment.
     * @param windSpeed as is loaded from the database - intended to work in conjunction with Station class.
     * @return int in Beufort format
     */
    public static int convertToBeufort(int windSpeed){

        if (windSpeed >= 103){
            windSpeed = 11;
        } else if (windSpeed >= 89) {
            windSpeed = 10;
        } else if (windSpeed >= 75) {
            windSpeed = 9;
        } else if (windSpeed >= 62) {
            windSpeed = 8;
        } else if (windSpeed >= 50) {
            windSpeed = 7;
        } else if (windSpeed >= 39){
            windSpeed = 6;
        } else if (windSpeed >= 29) {
            windSpeed = 5;
        } else if (windSpeed >= 20) {
            windSpeed = 4;
        } else if (windSpeed >= 12) {
            windSpeed = 3;
        } else if (windSpeed >= 6) {
            windSpeed = 2;
        } else if (windSpeed >= 1) {
            windSpeed = 1;
        } else {
            windSpeed = 0;
        }
        return windSpeed;
    }

    /**
     * This method calculates the windchill factor and rounds the resulting value to 1 decimal point
     * @param windSpeed from the database - intended to be used with Station class
     * @param temperature most recent from the database - should be used with Station class
     * @return double Wind Chill calculation that is rounded to .0
     */
    public static double toWindChill(double windSpeed, double temperature){
        double windChill = 13.12 + 0.6215 * temperature - 11.37 * Math.pow(windSpeed, 0.16)
                + 0.3965 * temperature * Math.pow(windSpeed, 0.16);
        windChill = Math.round(windChill * 10) / 10.0; // rounds the answer to 1 decimal point
        return windChill;
    }

    /**
     * This method converts the wind direction in degrees into a String value.
     * @param direction wind direction from the database
     * @return String of wind direction in String format
     */
    public static String windDirectionToString(int direction){
        String windDirection;
        if (direction >= 326.25){
            windDirection = "North North West";
        } else if (direction >= 303.75) {
            windDirection = "North West";
        } else if (direction >= 281.25) {
            windDirection = "West North West";
        } else if (direction >= 258.75) {
            windDirection = "West";
        } else if (direction >= 236.25) {
            windDirection = "West South West";
        } else if (direction >= 213.75) {
            windDirection = "South West";
        } else if (direction >= 191.25) {
           windDirection = "South South West";
        } else if (direction >= 168.75) {
            windDirection = "South";
        } else if (direction >= 146.25) {
            windDirection = "South South East";
        } else if (direction >= 123.75) {
            windDirection = "South East";
        } else if (direction >= 101.25) {
            windDirection = "East South East";
        } else if (direction >= 78.75) {
            windDirection = "East";
        } else if (direction >= 56.25) {
            windDirection = "East North East";
        } else if (direction >= 33.75) {
            windDirection = "North East";
        } else if (direction >= 11.25) {
            windDirection = "North North East";
        } else {
            windDirection = "North";
        }
        return windDirection;
    }

    /**
     * This method is used for rounding numbers to 3 decimal points.
     * @param num any number that the user might need to round to .000 decimal places. In this case used for Lat at Lng
     * @return double rounded to .000 decimal places.
     */
    public static double rounding(float num){
        double k;
        k = Math.round(num * 1000.0) / 1000.0;
        return k;
    }
    
}
