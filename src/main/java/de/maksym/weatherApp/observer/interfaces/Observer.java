package de.maksym.weatherApp.observer.interfaces;

public interface Observer {
    void update(double maxTemp, double minTemp,double humidity, double pressure, double temp);
}
