package de.maksym.weatherApp.web.DAO;

import de.maksym.weatherApp.observer.interfaces.Subject;
import de.maksym.weatherApp.observer.subject.WeatherSubject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class WeatherDAO {
    final Subject subject;

    @Autowired
    public WeatherDAO(WeatherSubject subject) {
        this.subject = subject;
    }

    public void initialize(String city) {
        String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=c3aaabcddb63080b0079bd7df9815848");
        JSONObject object = null;
        if(!output.isEmpty()) {
            object = new JSONObject(output);
        }
        subject.setMeasurements(object.getJSONObject("main").getDouble("temp_max"),
                object.getJSONObject("main").getDouble("temp_min"),
                object.getJSONObject("main").getDouble("humidity"),
                object.getJSONObject("main").getDouble("pressure"),
                object.getJSONObject("main").getDouble("temp"));
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
