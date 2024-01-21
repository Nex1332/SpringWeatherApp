package de.maksym.weatherApp.repository;

import de.maksym.weatherApp.interfaces.Subject;
import de.maksym.weatherApp.subject.WeatherSubject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class WeatherRepository{
    final WeatherSubject weatherSubject;

    @Autowired
    public WeatherRepository(WeatherSubject weatherSubject) {
        this.weatherSubject = weatherSubject;
    }

    public void initialize(String city) {
        String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=c3aaabcddb63080b0079bd7df9815848");
        JSONObject object = null;
        if(!output.isEmpty()) {
            object = new JSONObject(output);
            System.out.println("Температура " + object.getJSONObject("main").getDouble("temp"));
            System.out.println("Ощущается " + object.getJSONObject("main").getDouble("feels_like"));
            System.out.println("Максимум " + object.getJSONObject("main").getDouble("temp_max"));
            System.out.println("Минимум " + object.getJSONObject("main").getDouble("temp_min"));
            System.out.println("Давление " + object.getJSONObject("main").getDouble("pressure"));
        }
        weatherSubject.setMeasurements(object.getJSONObject("main").getDouble("temp_max"),
                object.getJSONObject("main").getDouble("temp_min"),
                object.getJSONObject("main").getDouble("pressure"));
    }

    public static String getUrlContent(String urlAddress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Не правильное название города, попробуйте ввести с использованием букв Латиницы.");
        }
        return content.toString();
    }
}
