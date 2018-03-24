package com.example.hackbdx.network;

/**
 * Created by canom on 24/3/2018.
 */

public class Main {
    private String temp;
    private String pressure;
    private String humidity;
    private String temp_min;
    private String temp_max;

    /*public Main(){
        temp = temp;
        pressure = "";
        humidity = "";
        temp_min = "";
        temp_max = "";
    }*/

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

}
