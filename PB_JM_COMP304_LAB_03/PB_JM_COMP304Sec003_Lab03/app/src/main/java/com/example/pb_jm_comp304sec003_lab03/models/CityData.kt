package com.example.pb_jm_comp304sec003_lab03.models

/*

Response JSON

{
    "id": 317748,
    "name": "Toronto",
    "region": "Ontario",
    "country": "Canada",
    "lat": 43.67,
    "lon": -79.42,
    "url": "toronto-ontario-canada"
}
 */

data class CityData(
        val name: String,
        val region: String,
        val country: String,
)
