package de.maksym.weatherApp.web.controller;

import de.maksym.weatherApp.observer.displays.AverageTemperatureDisplay;
import de.maksym.weatherApp.observer.displays.CurrentConditionsDisplay;
import de.maksym.weatherApp.observer.displays.ForeCastDisplay;
import de.maksym.weatherApp.observer.displays.SystemDisplay;
import de.maksym.weatherApp.web.DAO.WeatherDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class WebController{
    final AverageTemperatureDisplay averageTemperatureDisplay;
    final CurrentConditionsDisplay currentConditionsDisplay;
    final ForeCastDisplay foreCastDisplay;
    final SystemDisplay systemDisplay;
    final WeatherDAO weatherDAO;
    public WebController(AverageTemperatureDisplay averageTemperatureDisplay,
                         CurrentConditionsDisplay currentConditionsDisplay,
                         ForeCastDisplay foreCastDisplay, SystemDisplay systemDisplay, WeatherDAO weatherDAO) {
        this.averageTemperatureDisplay = averageTemperatureDisplay;
        this.currentConditionsDisplay = currentConditionsDisplay;
        this.foreCastDisplay = foreCastDisplay;
        this.systemDisplay = systemDisplay;
        this.weatherDAO = weatherDAO;
    }

    @GetMapping()
    public String start(){
        return "EnteringTheCityName";
    }

    @PostMapping("/weather")
    public String enterHomePage(@ModelAttribute("city") String city, Model model){
        try {
            model.addAttribute("currentCity", city);
            weatherDAO.initialize(city);
        } catch (IOException e){
            return "exception/Exception";
        }
        return "HomePage";
    }

    @GetMapping("/weather/{city}")
    public String returnHomePage(Model model, @PathVariable("city") String city){
        try {
            model.addAttribute("currentCity", city);
            weatherDAO.initialize(city);
        } catch (IOException e){
            return "exception/Exception";
        }
        return "HomePage";
    }

    @GetMapping("/averageTemperature/{city}")
    public String averageTemperature(Model model, @PathVariable("city") String city){
        try {
            weatherDAO.initialize(city);
            model.addAttribute("averageTemperatureDisplay", averageTemperatureDisplay);
        } catch (IOException e) {
            System.out.println("Всё сломалось)");
        }
        return "display/Temperature";
    }

    @GetMapping("/currentConditions/{city}")
    public String currentConditions(Model model, @PathVariable("city") String city){
        try {
            weatherDAO.initialize(city);
            model.addAttribute("currentConditionsDisplay", currentConditionsDisplay);
        } catch (IOException e) {
            System.out.println("Всё сломалось)");
        }
        return "display/ActualConditions";
    }

    @GetMapping("/foreCast/{city}")
    public String foreCast(Model model, @PathVariable("city") String city){
        try {
            weatherDAO.initialize(city);
            model.addAttribute("foreCastDisplay", foreCastDisplay.display());
        } catch (IOException e) {
            System.out.println("Всё сломалось)");
        }
        return "display/ForeCast";
    }

    @GetMapping("/sys/{city}")
    public String System(Model model, @PathVariable("city") String city){
        try {
            weatherDAO.initialize(city);
            model.addAttribute("system", systemDisplay);
        } catch (IOException e) {
            System.out.println("Всё сломалось)");
        }
        return "display/SunriseAndSunset";
    }
}
