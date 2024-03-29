package de.maksym.weatherApp.observer.displays;

import de.maksym.weatherApp.observer.Conversion;
import de.maksym.weatherApp.observer.interfaces.DisplayElements;
import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;
import org.springframework.stereotype.Component;

@Component
public class CurrentConditionsDisplay implements Observer, DisplayElements {
    private Subject subject;
    private Conversion conversion;
    double temp;
    double humidity;

    public CurrentConditionsDisplay(Subject subject, Conversion conversion) {
        this.subject = subject;
        this.conversion = conversion;
        subject.registerObserver(this);
    }

    @Override
    public String display() {
        return temp + "C temp and " + humidity + " humidity";
    }

    @Override
    public void update(double maxTempKelvins, double minTempKelvins,
                       double humidity, double pressure,
                       double tempKelvins, long sunrise, long sunset) {
        this.temp = conversion.conversionFromKelvinsToCelsius(tempKelvins);
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }
}
