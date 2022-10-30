package com.xanthenite.isining.composeapp.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.xanthenite.isining.composeapp.ui.screens.auth.ForgotScreen
import com.xanthenite.isining.composeapp.ui.screens.auth.LoginScreen
import com.xanthenite.isining.composeapp.ui.screens.auth.RegisterScreen
import com.xanthenite.isining.composeapp.ui.screens.auth.TwoFactorAuthScreen

fun NavGraphBuilder.authNavGraph(navController : NavHostController)
{
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthScreen.Login.route)
    {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                    viewModel = hiltViewModel() ,
                    onNavigateToSignUp = { navController.navigate(AuthScreen.SignUp.route) },
                    onNavigateToHome = { navController.popBackStack()
                                         navController.navigate(Graph.HOME)},
                    onNavigateToForgot = {navController.navigate(AuthScreen.Forgot.route)},
                    onNavigateToTwoFactor = { navController.navigate(AuthScreen.TwoFactor.route) })
        }
        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(
                    onNavigateUp = { navController.navigateUp()} ,
                    viewModel = hiltViewModel())
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotScreen(onNavigateUp = { navController.navigateUp()} ,
                         viewModel = hiltViewModel())
        }
        composable(route = AuthScreen.TwoFactor.route) {
            TwoFactorAuthScreen(viewModel = hiltViewModel(),
                                onNavigateToHome = { navController.popBackStack()
                                                     navController.navigate(Graph.HOME) },
                                onNavigateUp = { navController.navigateUp() })
        }
    }
}

///**
//* Launches Signup screen
//*/
//fun NavController.navigateToSignup() = navigate(AuthScreen.SignUp.route)
//
///**
//* Clears backstack including current screen and navigates to Home Screen
//*/
//fun NavController.popAllAndNavigateToMain() = navigate(Graph.HOME) {
//    launchSingleTop = true
//    popUpTo(Graph.HOME)
//}


sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
    object TwoFactor : AuthScreen(route = "TWO_FACTOR")
}
