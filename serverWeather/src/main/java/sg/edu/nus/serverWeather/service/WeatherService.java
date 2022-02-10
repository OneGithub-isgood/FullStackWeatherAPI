package sg.edu.nus.serverWeather.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import sg.edu.nus.serverWeather.model.*;

@Service
public class WeatherService {

    public List<Weather> getWeather(String city) {

        final String urlAddQuery = UriComponentsBuilder //"https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=%WeatherApiKey%";
            .fromUriString("https://api.openweathermap.org//data/2.5/weather")
            .queryParam("q", city.replaceAll(" ", "\\+"))
            .queryParam("appid", System.getenv("WEATHER_API_KEY"))
            .queryParam("units", "metric")
            .toUriString();
        
        final RequestEntity<Void> req = RequestEntity
            .get(urlAddQuery)
            .accept(MediaType.APPLICATION_JSON)
            .build();
        final RestTemplate temp = new RestTemplate();
        final ResponseEntity<String> resp = temp.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException(
                "Error Code %s".formatted(resp.getStatusCode().toString()));
        }
        final String respbody = resp.getBody();

        try (InputStream iS = new ByteArrayInputStream(respbody.getBytes())) {
            final JsonReader reader = Json.createReader(iS);
            final JsonObject data = reader.readObject();
            final JsonArray reading = data.getJsonArray("weather");
            if (!reading.isEmpty()) {
                final String cityName = data.getString("name");
                final float temperature = (float)data.getJsonObject("main").getJsonNumber("temp").doubleValue();
                return reading.stream()
                    .map(var -> (JsonObject)var)
                    .map(Weather::create)
                    .map(weatherInfo -> {
                        weatherInfo.setCityName(cityName);
                        weatherInfo.setTemperature(temperature);
                        return weatherInfo;
                    }).collect(Collectors.toList());
            }
        } catch (Exception ex) { }
        return Collections.EMPTY_LIST;
    }
}
