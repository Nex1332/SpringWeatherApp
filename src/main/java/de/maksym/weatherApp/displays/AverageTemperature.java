package de.maksym.weatherApp.displays;

import de.maksym.weatherApp.interfaces.DisplayElements;
import de.maksym.weatherApp.interfaces.Observer;

public class AverageTemperature implements Observer, DisplayElements {
    private float maxTemp;
    private float minTemp;
    @Override
    public void display() {

    }

    @Override
    public void update(double maxTemp, double minTemp, double pressure) {

    }
}
