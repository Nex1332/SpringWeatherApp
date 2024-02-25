package de.maksym.weatherApp.web.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.maksym.weatherApp.observer.interfaces.Subject;
import de.maksym.weatherApp.observer.subject.WeatherSubject;
import de.maksym.weatherApp.web.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class WeatherDAO {
    final Subject subject;

    @Autowired
    public WeatherDAO(WeatherSubject subject) {
        this.subject = subject;
    }

    public void initialize(String city) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse response = objectMapper.readValue(new URL("https://api.openweathermap.org/data/2.5/weather?q="
                + URLEncoder.encode(city, StandardCharsets.UTF_8)
                + "&appid=c3aaabcddb63080b0079bd7df9815848"), WeatherResponse.class);

        subject.setMeasurements(response.getMain().getTemp_max(),
                response.getMain().getTemp_min(),
                response.getMain().getHumidity(),
                response.getMain().getPressure(),
                response.getMain().getTemp(),
                response.getSys().getSunrise(),
                response.getSys().getSunset());
    }
}
