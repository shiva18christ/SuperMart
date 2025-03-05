package com.task.Supermarket.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class WeatherResponse {

    public Current current;
    public Location location;

    @Data
    public class Current {
        public int temperature;
        @JsonProperty("weather_descriptions")
        public ArrayList<String> weatherDescriptions;
        @JsonProperty("feelslike")
        public int feelsLike;
        @JsonProperty("wind_speed")
        public int windSpeed;
        @JsonProperty("wind_degree")
        public int windDegree;
        @JsonProperty("wind_dir")
        public String windDirection;
        public int pressure;
        @JsonProperty("precip")
        public int precipitataion;
        public int humidity;
        @JsonProperty("cloudcover")
        public int cloudCover;
        @JsonProperty("uv_index")
        public int uvIndex;
        public int visibility;

    }

    @Data
    public class Location {
        public String name;
        public String country;
        public String region;
        @JsonProperty("lat")
        public String latitude;
        @JsonProperty("lon")
        public String longitude;
        public String localtime;

    }


}









