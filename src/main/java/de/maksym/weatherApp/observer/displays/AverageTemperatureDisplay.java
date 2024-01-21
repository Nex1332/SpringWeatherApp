package de.maksym.weatherApp.observer.displays;

import de.maksym.weatherApp.observer.interfaces.DisplayElements;
import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;
import org.springframework.stereotype.Component;

@Component
public class AverageTemperatureDisplay implements Observer, DisplayElements {
    private Subject subject;
    private double maxTemp;
    private double averageTemp;
    private double minTemp;

    public AverageTemperatureDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(double maxTemp, double minTemp, double humidity, double pressure, double temp) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.averageTemp = (maxTemp + minTemp) / 2;
    }

    @Override
    public String display() {
        return "Average Temperature " + averageTemp + "F\n" +
                "Max Temperature " + maxTemp + "F\n" +
                "Min Temperature " + minTemp + "F";
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getAverageTemp() {
        return averageTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }
}
