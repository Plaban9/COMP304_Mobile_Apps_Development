package com.example.pb_jm_comp304sec003_lab03.views.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pb_jm_comp304sec003_lab03.Navigation.NavDestination
import com.example.pb_jm_comp304sec003_lab03.models.WeatherData
import com.example.pb_jm_comp304sec003_lab03.roomDB.RoomCityData
import com.example.pb_jm_comp304sec003_lab03.viewmodels.WeatherDataViewModel
import com.example.pb_jm_comp304sec003_lab03.views.HomeActivity.Companion.weatherDataList
import com.example.pb_jm_comp304sec003_lab03.views.HomeActivity.Companion.shouldQuery

@Composable
fun HomeScreen(navController: NavController, viewModel: WeatherDataViewModel, roomCityDataList: State<List<RoomCityData>?>, cityString: String?)
{
    //TODO: Check JSON Here
    Text(
            text = cityString.toString(), modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.BottomCenter)
    )
    MainUI(navController = navController, viewModel = viewModel, cityString.toString())
}

@Composable
private fun MainUI(navController: NavController, viewModel: WeatherDataViewModel, city: String)
{
    //TODO: Array of Cities


    Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopAppBarUI(navController = navController) }
    ) { innerPadding ->
        ContentUI(city, innerPadding, viewModel)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarUI(navController: NavController)
{
    TopAppBar(
            title = { Text(text = "Weather App 🌦️") },
            colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            actions = {
                IconButton(
                        onClick = { searchBarUI(navController = navController) }
                ) {
                    Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search City",
                    )
                }
            }
    )
}


//TODO: Custom City
@Composable
private fun ContentUI(city: String, innerPaddingValues: PaddingValues, viewModel: WeatherDataViewModel)
{
    viewModel.fetchCityDBList()

    val roomCityDataList by viewModel.cityDBList.observeAsState()

//    val weatherDataList = mutableListOf<WeatherData>()

    if (roomCityDataList == null)
    {
        Text(text = "Loading...")
    }
    else
    {
        roomCityDataList!!.forEachIndexed { index, data ->
            viewModel.fetchCityWeatherData(data.name)
            val weatherData = viewModel.weatherData.observeAsState()
            if (weatherData.value != null && !weatherDataList.contains(weatherData.value))
                weatherDataList.add(weatherData.value!!)
        }
    }
    LazyVerticalGrid(
            modifier = Modifier.padding(innerPaddingValues),
            columns = GridCells.Fixed(count = 2)
    ) {

        items(weatherDataList) { weatherData ->
            WeatherCard(weatherData)
        }
//            item {
//                WeatherCard(weatherData = roomCityDataList!!)
//                //Text(text = weatherData.toString())
//            }
//            item {
//                WeatherCard(weatherData = roomCityDataList!!)
//                //Text(text = weatherData.toString())
//            }
//            item {
//                WeatherCard(weatherData = roomCityDataList!!)
//                //Text(text = weatherData.toString())
//            }

    }
}

private fun searchBarUI(navController: NavController)
{
    navController.navigate(NavDestination.SearchScreen.createRoute("Toronto"))
}
