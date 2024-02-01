package de.maksym.weatherApp.web.controller;

import de.maksym.weatherApp.observer.displays.AverageTemperatureDisplay;
import de.maksym.weatherApp.observer.displays.CurrentConditionsDisplay;
import de.maksym.weatherApp.observer.displays.ForeCastDisplay;
import de.maksym.weatherApp.web.DAO.WeatherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController{

    @Autowired AverageTemperatureDisplay averageTemperatureDisplay;
    @Autowired CurrentConditionsDisplay currentConditionsDisplay;
    @Autowired ForeCastDisplay foreCastDisplay;
    @Autowired WeatherDAO weatherDAO;
    String currentCity;

    @GetMapping()
    public String start(){
        return "/start";
    }

    @PostMapping("/weather")
    public String weatherApp(@ModelAttribute("city") String city){
        currentCity = city;
        return "/homePage";
    }

    @GetMapping("/averageTemperature")
    public String averageTemperature(Model model){
        weatherDAO.initialize(currentCity);
        model.addAttribute("averageTemperatureDisplay", averageTemperatureDisplay);
        return "/temperature";
    }

    @GetMapping("/currentConditions")
    public String currentConditions(Model model){
        weatherDAO.initialize(currentCity);
        model.addAttribute("currentConditionsDisplay", currentConditionsDisplay);
        return "/conditions";
    }

    @GetMapping("/foreCast")
    public String foreCast(Model model){
        weatherDAO.initialize(currentCity);
        model.addAttribute("foreCastDisplay", foreCastDisplay.display());
        return "/cast";
    }

}
