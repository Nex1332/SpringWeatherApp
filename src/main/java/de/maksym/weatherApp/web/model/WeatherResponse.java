package de.maksym.weatherApp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private String base;
    private WeatherMain main;
    private WeatherSystem sys;

    public WeatherSystem getSys() {
        return sys;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setSys(WeatherSystem sys) {
        this.sys = sys;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }
}
