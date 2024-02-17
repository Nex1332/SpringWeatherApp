package de.maksym.weatherApp.web.controller;

import de.maksym.weatherApp.observer.displays.AverageTemperatureDisplay;
import de.maksym.weatherApp.observer.displays.CurrentConditionsDisplay;
import de.maksym.weatherApp.observer.displays.ForeCastDisplay;
import de.maksym.weatherApp.web.DAO.WeatherDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class WebController{

    final AverageTemperatureDisplay averageTemperatureDisplay;
    final CurrentConditionsDisplay currentConditionsDisplay;
    final ForeCastDisplay foreCastDisplay;
    final WeatherDAO weatherDAO;
    String currentCity;

    public WebController(AverageTemperatureDisplay averageTemperatureDisplay,
                         CurrentConditionsDisplay currentConditionsDisplay,
                         ForeCastDisplay foreCastDisplay, WeatherDAO weatherDAO) {
        this.averageTemperatureDisplay = averageTemperatureDisplay;
        this.currentConditionsDisplay = currentConditionsDisplay;
        this.foreCastDisplay = foreCastDisplay;
        this.weatherDAO = weatherDAO;
    }

    @GetMapping()
    public String start(){
        return "/start";
    }

    @PostMapping("/weather")
    public String weatherApp(@ModelAttribute("city") String city){
        try {
            currentCity = city;
            weatherDAO.initialize(city);
        } catch (IOException e){
            return "/falseCity";
        }
        return "/homePage";
    }

    @GetMapping("/averageTemperature")
    public String averageTemperature(Model model){
        try {
            weatherDAO.initialize(currentCity);
            model.addAttribute("averageTemperatureDisplay", averageTemperatureDisplay);
        } catch (IOException e) {
            model.addAttribute("errorMessage");
        }
        return "/temperature";
    }

    @GetMapping("/currentConditions")
    public String currentConditions(Model model){
        try {
            weatherDAO.initialize(currentCity);
            model.addAttribute("currentConditionsDisplay", currentConditionsDisplay);
        } catch (IOException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "/conditions";
    }

    @GetMapping("/foreCast")
    public String foreCast(Model model){
        try {
            weatherDAO.initialize(currentCity);
            model.addAttribute("foreCastDisplay", foreCastDisplay.display());
        } catch (IOException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "/cast";
    }

}
