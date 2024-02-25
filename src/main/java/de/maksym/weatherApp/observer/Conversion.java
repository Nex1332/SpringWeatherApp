package de.maksym.weatherApp.observer;

import org.springframework.stereotype.Component;

@Component
public class Conversion {
    public double conversionFromKelvinsToCelsius(double temp){
        return  Math.round((temp - 273.15) * 10.0) / 10.0;
    }
}
