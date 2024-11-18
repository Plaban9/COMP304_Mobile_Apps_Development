package com.example.pb_jm_comp304sec003_lab03.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pb_jm_comp304sec003_lab03.roomDB.RoomCityData
import com.example.pb_jm_comp304sec003_lab03.viewmodels.WeatherDataViewModel
import com.example.pb_jm_comp304sec003_lab03.views.screens.HomeScreen
import com.example.pb_jm_comp304sec003_lab03.views.screens.SearchScreen


@Composable
fun NavGraph(navHostController: NavHostController, viewModel: WeatherDataViewModel, roomCityDataList: State<List<RoomCityData>?>)
{
    NavHost(navController = navHostController, startDestination = NavDestination.HomeScreen.route)
    {
        composable(NavDestination.HomeScreen.route)
        { cityJson ->
            val cityJsonString = cityJson.savedStateHandle.get<String>("cityData")
            HomeScreen(navHostController, viewModel, roomCityDataList, cityJsonString)
        }

        composable(
                NavDestination.SearchScreen.route.plus("/{city_data}"),
                arguments = listOf(navArgument("city_data")
                {
                    type = NavType.StringType
                })
        )
        {
            SearchScreen(navHostController, viewModel)
        }
    }

}