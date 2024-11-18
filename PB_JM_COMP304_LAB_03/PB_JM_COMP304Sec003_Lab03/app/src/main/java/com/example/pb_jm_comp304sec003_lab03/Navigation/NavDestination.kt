package com.example.pb_jm_comp304sec003_lab03.Navigation

sealed class NavDestination(val route: String)
{
    object HomeScreen : NavDestination("HomeScreen")
    object SearchScreen : NavDestination("SearchScreen")
    {
        fun createRoute(valueToPass: String): String
        {
            return "SearchScreen/$valueToPass"
        }
    }
}