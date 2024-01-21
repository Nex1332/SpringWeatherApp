package de.maksym.weatherApp.subject;

import de.maksym.weatherApp.interfaces.Observer;
import de.maksym.weatherApp.interfaces.Subject;
import de.maksym.weatherApp.repository.WeatherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class WeatherSubject implements Subject {
    private ArrayList observers;
    private double maxTemp;
    private double minTemp;
    private double pressure;


    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherSubject(WeatherRepository weatherRepository) {

        this.weatherRepository = weatherRepository;
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
            Observer observer = (Observer)observers.get(i);
            observer.update(maxTemp, minTemp, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(double maxTemp, double minTemp, double pressure) {
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.minTemp = minTemp;
        measurementsChanged();
    }
}
