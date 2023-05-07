package utilities;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Conversion extends Model{

    //converts weather code to string
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

    //converts celcius to fahrenheit
    public static double convertToFahrenheit(double temperature){
        return temperature * 9 / 5 + 32;
    }

    // wind speed to beufort scale
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

    public static double toWindChill(double windSpeed, double temperature){
        double windChill = 13.12 + 0.6215 * temperature - 11.37 * Math.pow(windSpeed, 0.16)
                + 0.3965 * temperature * Math.pow(windSpeed, 0.16);
        windChill = Math.round(windChill * 10) / 10.0; // rounds the answer to 1 decimal point
        return windChill;
    }

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
    
}
