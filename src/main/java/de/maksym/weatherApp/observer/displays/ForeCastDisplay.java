package de.maksym.weatherApp.observer.displays;

import de.maksym.weatherApp.observer.Conversion;
import de.maksym.weatherApp.observer.interfaces.DisplayElements;
import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;
import org.springframework.stereotype.Component;

@Component
public class ForeCastDisplay implements Observer, DisplayElements {
    private Subject subject;
    private Conversion conversion;
    private double minTemp;

    public ForeCastDisplay(Subject subject, Conversion conversion) {
        this.subject = subject;
        this.conversion = conversion;
        subject.registerObserver(this);
    }

    @Override
    public String display() {
        if(minTemp>10){
            return "Weather is going to be better, get your sunglasses ready";
        } else
            return "Weather is still pretty shit, get your warm clothes ready";
    }

    @Override
    public void update(double maxTempKelvins, double minTempKelvins,
                       double humidity, double pressure,
                       double tempKelvins, long sunrise, long sunset) {
        this.minTemp = conversion.conversionFromKelvinsToCelsius(minTempKelvins);
    }

}
