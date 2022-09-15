package com.xanthenite.isining.composeapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xanthenite.isining.composeapp.component.bottombar.BottomBarHomeItem
import com.xanthenite.isining.composeapp.ui.screens.home.HomeScreen

@Composable
fun MainNavGraph(navController : NavHostController)
{
    NavHost(navController = navController ,
            route = Graph.HOME,
            startDestination = BottomBarHomeItem.Home.route) {
        composable(route = BottomBarHomeItem.Home.route)
        {
            HomeScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToLogin = { navController.popBackStack()
                                          navController.navigate(Graph.AUTHENTICATION)})
        }
        authNavGraph(navController)
    }
}

///**
// * Clears backstack including current screen and navigates to Login Screen
// */
//fun NavController.popAllAndNavigateToLogin() = navigate(AuthScreen.Login.route) {
//    launchSingleTop = true
//    popUpTo(Graph.AUTHENTICATION)
//}
