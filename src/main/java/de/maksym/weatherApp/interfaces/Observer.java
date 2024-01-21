package de.maksym.weatherApp.interfaces;

public interface Observer {
    void update(double maxTemp, double minTemp, double pressure);
}
