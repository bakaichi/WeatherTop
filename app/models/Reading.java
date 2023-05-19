package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * This class is used to store weather readings in conjunction to the data.yml file
 *
 * @author Aidas Vaiciunas
 * @version 1.0
 */
@Entity
public class Reading extends Model
{
    // fields
    private int code;
    private double temperature;
    private double windSpeed;
    private int pressure;
    private int windDirection;

    /**
     * Constructor for Reading
     */
    public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure)
    {
        this.code = code;
        this.temperature= temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }



    // getters //
    public int getCode() {
        return code;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public int getWindDirection() {
        return windDirection;
    }


    // setters //
    public void setCode(int code) {
        this.code = code;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }
}