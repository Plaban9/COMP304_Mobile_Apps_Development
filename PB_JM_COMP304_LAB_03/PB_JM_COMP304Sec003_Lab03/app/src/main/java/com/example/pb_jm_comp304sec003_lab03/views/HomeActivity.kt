package com.example.pb_jm_comp304sec003_lab03.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.pb_jm_comp304sec003_lab03.Navigation.NavGraph
import com.example.pb_jm_comp304sec003_lab03.data.WeatherDataRepository
import com.example.pb_jm_comp304sec003_lab03.models.WeatherData
import com.example.pb_jm_comp304sec003_lab03.roomDB.CityDatabase
import com.example.pb_jm_comp304sec003_lab03.ui.theme.PB_JM_COMP304Sec003_Lab03Theme
import com.example.pb_jm_comp304sec003_lab03.viewmodels.ViewModelFactory
import com.example.pb_jm_comp304sec003_lab03.viewmodels.WeatherDataViewModel

class HomeActivity : ComponentActivity()
{
    //val viewModel: WeatherDataViewModel by viewModels()

    companion object
    {
        //        val viewModel: WeatherDataViewModel by viewModels()
        var weatherDataList = mutableStateListOf<WeatherData>()
        var shouldQuery = true
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val database = CityDatabase.getCityDatabase(applicationContext)
        val repository = WeatherDataRepository(database.roomCityDao())
        val viewModelFactory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[WeatherDataViewModel::class.java]

        setContent {
            PB_JM_COMP304Sec003_Lab03Theme {
                val roomCityDataList = viewModel.cityDBList.observeAsState()
                val navController = rememberNavController()
                NavGraph(navHostController = navController, viewModel, roomCityDataList)
            }
        }
    }
//
//    @Preview
//    @Composable
//    private fun MainUI(){
//        Scaffold(
//            modifier = Modifier.fillMaxSize(),
//            topBar = { TopAppBarUI() }
//        ) { innerPadding ->
//                ContentUI(innerPadding)
//        }
//    }
//
//    @Composable
//    @OptIn(ExperimentalMaterial3Api::class)
//    private fun TopAppBarUI() {
//        TopAppBar(
//            title = { Text(text = "Weather App 🌦️") },
//            colors = TopAppBarDefaults.topAppBarColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                titleContentColor = MaterialTheme.colorScheme.primary,
//            ),
//            actions = {
//                IconButton(
//                    onClick = { searchBarUI(this@HomeActivity) }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search City",
//                    )
//                }
//            }
//        )
//    }
//
//    @Composable
//    private fun ContentUI(innerPaddingValues: PaddingValues) {
//
//        viewModel.fetchCityWeatherData(city = "Toronto")
//
//        val weatherData by viewModel.weatherData.observeAsState(initial = null)
//
//        if (weatherData == null) {
//            Text(text = "Loading...")
//        } else {
//            LazyColumn(
//                modifier = Modifier.padding(innerPaddingValues),
//            ) {
////                items(weatherDataList) { weatherData ->
////                    WeatherCard(weatherData)
////                }
//                item {
//                    WeatherCard(weatherData = weatherData!!)
//                    Text(text = weatherData.toString())
//                }
//            }
//        }
//    }
//
//    private fun searchBarUI(context: Context) {
//        val intent = Intent(context, SearchActivity::class.java)
//        context.startActivity(intent)
//    }
}
