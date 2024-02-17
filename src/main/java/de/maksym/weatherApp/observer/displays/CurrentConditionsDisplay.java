package de.maksym.weatherApp.observer.displays;

import de.maksym.weatherApp.observer.interfaces.DisplayElements;
import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;
import org.springframework.stereotype.Component;

@Component
public class CurrentConditionsDisplay implements Observer, DisplayElements {
    private Subject subject;
    double temp;
    double humidity;

    public CurrentConditionsDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public String display() {
        return temp + "C temp and " + humidity + " humidity";
    }

    @Override
    public void update(double maxTemp, double minTemp, double humidity, double pressure, double temp) {
        this.temp = Math.round((temp - 273.15) * 10.0) / 10.0;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }
}
