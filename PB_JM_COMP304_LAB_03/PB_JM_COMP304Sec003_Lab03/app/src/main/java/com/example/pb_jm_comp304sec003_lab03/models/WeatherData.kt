package com.example.pb_jm_comp304sec003_lab03.models

import com.google.gson.annotations.SerializedName

/*

Response JSON

{
    "location": {
        "name": "Toronto",
        "region": "Ontario",
        "country": "Canada",
        "lat": 43.6667,
        "lon": -79.4167,
        "tz_id": "America/Toronto",
        "localtime_epoch": 1731284519,
        "localtime": "2024-11-10 19:21"
    },
    "current": {
        "last_updated_epoch": 1731284100,
        "last_updated": "2024-11-10 19:15",
        "temp_c": 11.1,
        "temp_f": 52.0,
        "is_day": 0,
        "condition": {
            "text": "Mist",
            "icon": "//cdn.weatherapi.com/weather/64x64/night/143.png",
            "code": 1030
        },
        "wind_mph": 8.1,
        "wind_kph": 13.0,
        "wind_degree": 152,
        "wind_dir": "SSE",
        "pressure_mb": 1006.0,
        "pressure_in": 29.7,
        "precip_mm": 0.0,
        "precip_in": 0.0,
        "humidity": 100,
        "cloud": 100,
        "feelslike_c": 9.5,
        "feelslike_f": 49.1,
        "windchill_c": 8.4,
        "windchill_f": 47.1,
        "heatindex_c": 10.5,
        "heatindex_f": 50.9,
        "dewpoint_c": 10.3,
        "dewpoint_f": 50.5,
        "vis_km": 4.8,
        "vis_miles": 2.0,
        "uv": 0.0,
        "gust_mph": 12.7,
        "gust_kph": 20.4,
        "air_quality": {
            "co": 344.1,
            "no2": 19.24,
            "o3": 56.0,
            "so2": 7.215,
            "pm2_5": 4.625,
            "pm10": 6.475,
            "us-epa-index": 1,
            "gb-defra-index": 1
        }
    }
}
 */

data class WeatherData(
    val location: LocationData,
    val current: Current,
)

data class LocationData(
    val name: String,
    val region: String,
    val country: String,
)

data class Current(
    val temp_c: Double,
    val humidity: Double,
    @SerializedName("feelslike_c")
    val feelsLikeC: Double,
    val uv: Double,

    val condition: Condition,

    @SerializedName("air_quality")
    val airQuality: AQI,
)

data class Condition(
    val text: String,
    val icon: String,
)

data class AQI(
    val co: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val pm2_5: Double,
    val pm10: Double,
)
