package de.maksym.weatherApp.observer.subject;

import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class WeatherSubject implements Subject {
    private ArrayList<Observer> observers;
    private double temp;
    private double maxTemp;
    private double minTemp;
    private double humidity;
    private double pressure;
    private long sunset;
    private long sunrise;

    public WeatherSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(maxTemp, minTemp,humidity, pressure, temp, sunrise, sunset);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    @Override
    public void setMeasurements(double maxTemp, double minTemp,
                                double humidity, double pressure,
                                double temp, long sunrise, long sunset) {
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.minTemp = minTemp;
        this.temp = temp;
        this.sunrise = sunrise;
        this.sunset = sunset;

        measurementsChanged();
    }
}
