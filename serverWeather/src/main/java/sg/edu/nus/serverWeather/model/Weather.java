package sg.edu.nus.serverWeather.model;

import jakarta.json.JsonObject;

public class Weather {
    private String cityName;
    private String weatherCond;
    private String condDescription;
    private String thumbnail;
    private float temperature;

    public static Weather create(JsonObject jO) {
        final Weather currWeather = new Weather();
        currWeather.setWeatherCond(jO.getString("main"));
        currWeather.setcondDescription(jO.getString("description"));
        currWeather.setThumbnail(jO.getString("icon"));
        return currWeather;
    }

    public void setCityName(String city) {
        this.cityName = city;
    }
    
    public void setWeatherCond(String weatherCond) {
        this.weatherCond = weatherCond;
    }

    public void setcondDescription(String description) {
        this.condDescription = description;
    }

    public void setThumbnail(String iconCode) {
        this.thumbnail = "http://openweathermap.org/img/wn/%s@2x.png".formatted(iconCode);
    }

    public void setTemperature(float temp) {
        this.temperature = temp;
    }

    public String getWeatherCond() {
        return this.weatherCond;
    }

    public String getCondDescription() {
        return this.condDescription;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public float getTemperature() {
        return this.temperature;
    }
}
