package de.maksym.weatherApp.observer.interfaces;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    void setMeasurements(double maxTemp, double minTemp, double humidity, double pressure, double temp);
}
