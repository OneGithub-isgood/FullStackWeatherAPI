package sg.edu.nus.serverWeather.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.serverWeather.service.WeatherService;

@RestController
@RequestMapping(path="api/weather", produces=MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {
    private final Logger logger = Logger.getLogger(WeatherController.class.getName());

    @Autowired
    private WeatherService weatherServ;

    @GetMapping(path="{city}")
    public ResponseEntity<String> getWeatherData(@PathVariable String city) {
        logger.log(Level.INFO, "City : %s".formatted(city)+".");
        String currWeatherCond = weatherServ.getWeather(city).get(0).getWeatherCond().toString();
        String currWeatherDescription = weatherServ.getWeather(city).get(0).getCondDescription().toString();
        String currWeatherTemp = String.valueOf((weatherServ.getWeather(city).get(0).getTemperature()));
        String currWeatherIcon = weatherServ.getWeather(city).get(0).getThumbnail().toString();
        logger.log(Level.INFO, "Weather Icon : %s".formatted(currWeatherIcon)+".");

        if (currWeatherCond == null) {
            return ResponseEntity.badRequest().body(
                Json.createObjectBuilder()
                .add("error","City Not Found")
                .build()
                .toString());
        }
        final JsonObject weatherData = Json.createObjectBuilder()
            //.add("weather", Json.createArrayBuilder()
                //.add(Json.createObjectBuilder()
                    .add("main", currWeatherCond)
                    .add("description", currWeatherDescription)
                    .add("icon", currWeatherIcon)
                //)
            //)
            //.add("main", Json.createArrayBuilder()
                //.add(Json.createObjectBuilder()
                    .add("temp", currWeatherTemp)
                //)
            //)
            .add("city", city)
        .build();
        return ResponseEntity.ok(weatherData.toString());
    }
    
}
