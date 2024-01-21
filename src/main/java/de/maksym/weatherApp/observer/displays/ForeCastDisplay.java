package de.maksym.weatherApp.observer.displays;

import de.maksym.weatherApp.observer.interfaces.DisplayElements;
import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;
import org.springframework.stereotype.Component;

@Component
public class ForeCastDisplay implements Observer, DisplayElements {
    private Subject subject;

    private double maxTemp;

    public ForeCastDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public String display() {
        if(maxTemp>250){
            return "Weather is going to be better, get your sunglasses ready";
        } else
            return "Weather is still pretty shit, get your warm clothes ready";
    }

    @Override
    public void update(double maxTemp, double minTemp, double humidity, double pressure, double temp) {
        this.maxTemp = maxTemp;
    }

}
