package de.maksym.weatherApp.controller;

import de.maksym.weatherApp.subject.WeatherSubject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.maksym.weatherApp.repository.*;

@Controller
public class WebController{

    WeatherRepository weatherRepository;

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

    @GetMapping("/currentConditions")
    public String currentConditions(){

        return "/conditions";
    }

    @GetMapping("/averageTemperature")
    public String averageTemperature(){
        weatherRepository.initialize(currentCity);
        return "/temperature";
    }

    @GetMapping("/foreCast")
    public String foreCast(){
        weatherRepository.initialize(currentCity);
        return "/cast";
    }

}
