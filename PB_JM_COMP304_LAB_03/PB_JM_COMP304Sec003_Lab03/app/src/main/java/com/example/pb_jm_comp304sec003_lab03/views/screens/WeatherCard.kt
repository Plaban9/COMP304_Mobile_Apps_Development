package com.example.pb_jm_comp304sec003_lab03.views.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pb_jm_comp304sec003_lab03.R

import com.example.pb_jm_comp304sec003_lab03.models.WeatherData


@Composable
fun WeatherCard(weatherData: WeatherData)
{
    ElevatedCard(
            modifier = Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            elevation = CardDefaults.elevatedCardElevation(
                    hoveredElevation = 10.dp,
                    defaultElevation = 2.dp,
                    pressedElevation = 5.dp,
            ),
            onClick = { /* TODO: Goto full details activity */ }
    ) {
        Box(

                modifier = Modifier
                    .padding(10.dp)
                    .size(200.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.TopStart
        )
        {
            BackgroundImage(weatherData = weatherData)
            TemperatureEmoji(weatherData = weatherData)
            CityRegionCountry(weatherData = weatherData)
        }
    }
}


@Composable
fun BackgroundImage(weatherData: WeatherData)
{
    val backGroundImage = getBackgroundImage(weatherData = weatherData)

    Image(
            painter = painterResource(id = backGroundImage),
            contentDescription = null,
            modifier = Modifier
                .scale(scaleX = 1.1f, scaleY = 1.1f)
                .wrapContentSize(unbounded = true, align = Alignment.Center)
                .fillMaxSize()
                .blur(
                        radiusX = 2.dp,
                        radiusY = 2.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
    )
}

@Composable
fun TemperatureEmoji(weatherData: WeatherData)
{
    Row(
            modifier = Modifier
                .padding(1.dp)
//                .background(Red)
                .fillMaxWidth()
    ) {
        Column(
                modifier = Modifier
                    .padding(1.dp)
                    .wrapContentSize(align = Alignment.TopStart)
//                    .background(Blue)
                    .offset(y = -5.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                    modifier = Modifier
                        .padding(0.dp)
                        .align(Alignment.Start)
                        .wrapContentWidth(),
                    text = Math.round(weatherData.current.temp_c).toInt().toString(),
//                    style = MaterialTheme.typography.displayLarge,
                    style = TextStyle(
                            fontSize = 60.sp,
                            shadow = Shadow(
                                    color = Color.Black, offset = Offset(5f, 5f), blurRadius = 7f
                            )
                    ),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Left,
            )
        }
        Column(
                modifier = Modifier
                    .padding(1.dp)
                    .wrapContentSize(align = Alignment.Center)
//                    .background(Magenta)
                    .fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                    modifier = Modifier
                        .padding(1.dp)
                        .align(Alignment.Start)
                        .wrapContentWidth(),
                    text = " °C",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    style = TextStyle(
                            fontSize = 15.sp,
                            shadow = Shadow(
                                    color = Color.Black, offset = Offset(5f, 5f), blurRadius = 7f
                            )
                    )
            )
        }
        Column(
                modifier = Modifier
                    .padding(1.dp)
                    .wrapContentWidth(align = Alignment.End)
//                    .background(Green)
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
        ) {

            AsyncImage(
                    model = "https://" + weatherData.current.condition.icon,
                    contentScale = ContentScale.Crop,
                    clipToBounds = true,
                    contentDescription = "Weather Icon",
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .padding(0.dp)
//                        .height(128.dp)
//                        .width(128.dp)
                        .scale(scaleX = 3.75f, scaleY = 3.75f)
                        .offset(x = -2.dp, y = 2.dp)
                        .shadow(3.dp, shape = RectangleShape, clip = true, ambientColor = Black, spotColor = Black)
            )
        }
    }
}

@Composable
fun CityRegionCountry(weatherData: WeatherData)
{
    Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .wrapContentSize()
                .offset(y = 60.dp)
    )
    {
        Column(
                modifier = Modifier
                    .padding(1.dp)
                    .wrapContentSize(align = Alignment.Center),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {

            City(weatherData = weatherData)
            Region(weatherData = weatherData)
            Country(weatherData = weatherData)
        }
    }
}

@Composable
fun City(weatherData: WeatherData)
{
// City Name Text
    Text(
            modifier = Modifier
                .padding(1.dp),
            text = weatherData.location.name.toString(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            style = TextStyle(
                    fontSize = 40.sp,
                    shadow = Shadow(
                            color = Color.Black, offset = Offset(5f, 5f), blurRadius = 7f
                    )

            ),
            color = Color.White,
    )
}

@Composable
fun Region(weatherData: WeatherData)
{
    Text(
            modifier = Modifier
                .padding(1.dp),
            text = weatherData.location.region.toString(),
            textAlign = TextAlign.Center,
//                        fontWeight = FontWeight.Medium,
//            style = MaterialTheme.typography.titleMedium,
            style = TextStyle(
                    fontSize = 27.5.sp,
                    shadow = Shadow(
                            color = Color.Black, offset = Offset(5f, 5f), blurRadius = 5f
                    )

            ),
            color = Color.White,
    )
}

@Composable
fun Country(weatherData: WeatherData)
{
    Text(
            modifier = Modifier
                .padding(1.dp),
            text = weatherData.location.country.toString(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
//            style = MaterialTheme.typography.titleSmall,
            style = TextStyle(
                    fontSize = 18.sp,
                    shadow = Shadow(
                            color = Color.Black, offset = Offset(2f, 2f), blurRadius = 3f
                    )

            ),
            color = Color.White,
    )
}

fun getBackgroundImage(weatherData: WeatherData): Int
{
    if (weatherData.current.condition.text.contains("sunny", ignoreCase = true))
    {
        return R.drawable.sunny
    }
    else if (weatherData.current.condition.text.contains("snow", ignoreCase = true))
    {
        return R.drawable.snow_2
    }
    else if (weatherData.current.condition.text.contains("rain", ignoreCase = true))
    {
        return R.drawable.rain
    }
    else if (weatherData.current.condition.text.contains("thunder", ignoreCase = true))
    {
        return R.drawable.thunder
    }
    else if (weatherData.current.condition.text.contains("clear", ignoreCase = true))
    {
        return R.drawable.sunny
    }
    else if (weatherData.current.condition.text.contains("overcast", ignoreCase = true))
    {
        return R.drawable.overcast
    }
    else if (weatherData.current.condition.text.contains("haze", ignoreCase = true))
    {
        return R.drawable.fog
    }
    else if (weatherData.current.condition.text.contains("fog", ignoreCase = true))
    {
        return R.drawable.fog
    }
    else if (weatherData.current.condition.text.contains("cloudy", ignoreCase = true))
    {
        return R.drawable.cloudy
    }
    else if (weatherData.current.condition.text.contains("mist", ignoreCase = true))
    {
        return R.drawable.fog
    }

    return R.drawable.sunny
}