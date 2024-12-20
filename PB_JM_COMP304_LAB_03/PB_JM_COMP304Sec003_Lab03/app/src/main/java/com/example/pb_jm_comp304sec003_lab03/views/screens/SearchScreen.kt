package com.example.pb_jm_comp304sec003_lab03.views.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pb_jm_comp304sec003_lab03.models.CityData
import com.example.pb_jm_comp304sec003_lab03.roomDB.RoomCityData
import com.example.pb_jm_comp304sec003_lab03.viewmodels.WeatherDataViewModel
import com.example.pb_jm_comp304sec003_lab03.views.HomeActivity.Companion.shouldQuery

@Composable
fun SearchScreen(navController: NavController, viewModel: WeatherDataViewModel)
{
    var searchedCityText by rememberSaveable { mutableStateOf("") }

    Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopAppBarUI(searchedCityText, { searchedCityText = it }) },
    ) { innerPadding ->
        if (searchedCityText.length >= 3)
            ContentUI(innerPaddingValues = innerPadding, searchedCityText, navController = navController, viewModel = viewModel)
    }
}

@Composable
private fun TopAppBarUI(searchText: String, onSearchQueryChanged: (String) -> Unit)
{
    OutlinedTextField(
            value = searchText,
            onValueChange = onSearchQueryChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            leadingIcon = {
                Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(24.dp)
                )
            },
            placeholder = { Text(text = "Enter city name (at least 3 letters)") },
            singleLine = true,
            label = { Text(text = "Search") },
            isError = searchText.length < 3,
    )
}

@Composable
private fun ContentUI(innerPaddingValues: PaddingValues, cityText: String, navController: NavController, viewModel: WeatherDataViewModel)
{
    viewModel.fetchCityList(cityText)
    val cityList by viewModel.cityList.observeAsState(initial = emptyList())

    LazyColumn(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(15.dp)
    ) {
        items(cityList) { city ->
            SearchedCityUI(city = city, navController = navController, viewModel = viewModel)
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Composable
private fun SearchedCityUI(city: CityData, navController: NavController, viewModel: WeatherDataViewModel)
{
    ElevatedCard(
            onClick =
            {
                shouldQuery = true
                //TODO: Send Json here
                val roomCity = getRoomCityData(city)
                viewModel.insertCityIntoDB(roomCity)

                navController.previousBackStackEntry?.savedStateHandle?.set("cityData", city.name)
                navController.popBackStack()
            },
    ) {
        // City Name Text (Toronto)
        Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                text = city.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
        )

        // City Region Text (Ontario)
        Text(
                modifier = Modifier.fillMaxWidth(),
                text = city.region,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
        )

        // City Country Text (Canada)
        Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                text = city.country,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
        )
    }
}

private fun getRoomCityData(city: CityData) : RoomCityData {
    val roomCity = RoomCityData(
        name = city.name
    )

    return roomCity
}