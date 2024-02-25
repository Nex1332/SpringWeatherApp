package de.maksym.weatherApp.observer.interfaces;

public interface Observer {
    void update(double maxTempKelvins, double minTempKelvins, double humidity, double pressure, double tempKelvins, long sunrise, long sunset);
}
