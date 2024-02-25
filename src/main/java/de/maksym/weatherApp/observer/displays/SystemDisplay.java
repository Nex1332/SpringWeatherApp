package de.maksym.weatherApp.observer.displays;

import de.maksym.weatherApp.observer.interfaces.DisplayElements;
import de.maksym.weatherApp.observer.interfaces.Observer;
import de.maksym.weatherApp.observer.interfaces.Subject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Component
public class SystemDisplay implements DisplayElements, Observer {
    private Subject subject;
    private long sunrise;
    private long sunset;

    public SystemDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public String display() {
        return null;
    }

    @Override
    public void update(double maxTempKelvins, double minTempKelvins,
                       double humidity, double pressure,
                       double tempKelvins, long sunrise, long sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String conversationToTime(Long unixTimestamp){
        Date date = new Date(unixTimestamp * 1000L);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    public String getSunrise() {
        return conversationToTime(sunrise);
    }

    public String getSunset() {
        return conversationToTime(sunset);
    }
}
