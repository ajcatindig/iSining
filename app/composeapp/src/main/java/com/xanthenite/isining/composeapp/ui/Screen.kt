package com.xanthenite.isining.composeapp.ui

sealed class Screen(val route : String, val name : String) {
    object Welcome : Screen ("welcome", "Welcome")
    object Register : Screen("register", "Register")
    object Login : Screen("login", "Login")
    object Home : Screen("home", "Home")
}