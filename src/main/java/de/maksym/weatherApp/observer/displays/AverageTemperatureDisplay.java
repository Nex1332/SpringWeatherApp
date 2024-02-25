package de.maksym.weatherApp.observer.displays;

import de.maksym.weatherApp.observer.Conversion;
import de.maksym.weatherApp.observer.interfaces.DisplayElements;
import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;
import org.springframework.stereotype.Component;

@Component
public class AverageTemperatureDisplay implements Observer, DisplayElements {
    private Subject subject;
    private Conversion conversion;
    private double maxTemp;
    private double averageTemp;
    private double minTemp;

    public AverageTemperatureDisplay(Subject subject, Conversion conversion) {
        this.subject = subject;
        this.conversion = conversion;
        subject.registerObserver(this);
    }

    @Override
    public void update(double maxTempKelvins, double minTempKelvins,
                       double humidity, double pressure,
                       double tempKelvins, long sunrise, long sunset) {
        this.maxTemp = conversion.conversionFromKelvinsToCelsius(maxTempKelvins);
        this.minTemp = conversion.conversionFromKelvinsToCelsius(minTempKelvins);
        this.averageTemp = (maxTemp + maxTemp) / 2;
    }

    @Override
    public String display() {
        return null;
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
