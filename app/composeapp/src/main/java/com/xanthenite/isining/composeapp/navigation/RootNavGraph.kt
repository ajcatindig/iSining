package com.xanthenite.isining.composeapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xanthenite.isining.composeapp.ui.screens.home.HomeScreen
import com.xanthenite.isining.composeapp.ui.screens.main.MainScreen

@Composable
fun RootNavGraph(navController : NavHostController)
{
    NavHost(navController = navController ,
            route = Graph.ROOT,
            startDestination = Graph.AUTHENTICATION)
    {
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            MainScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val WELCOME = "welcome_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "main_graph"
    const val DETAILS = "details_graph"
}