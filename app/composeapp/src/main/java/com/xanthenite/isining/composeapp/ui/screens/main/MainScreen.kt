package com.xanthenite.isining.composeapp.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xanthenite.isining.composeapp.component.bottombar.BottomBarHomeItem
import com.xanthenite.isining.composeapp.navigation.MainNavGraph
import com.xanthenite.isining.composeapp.ui.theme.raleway
import com.xanthenite.isining.composeapp.ui.theme.typography

@Composable
fun MainScreen(navController : NavHostController = rememberNavController())
{
    val scaffoldState = rememberScaffoldState()
    Scaffold(bottomBar = { BottomBar(navController = navController) },
             scaffoldState = scaffoldState) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController : NavHostController) {
    val screens = listOf(
            BottomBarHomeItem.Home,
            BottomBarHomeItem.Exhibits,
            BottomBarHomeItem.Artworks,
            BottomBarHomeItem.Artists,
            BottomBarHomeItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarHeight = 56.dp

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.surface ,
                         contentColor = MaterialTheme.colors.onPrimary,
                         elevation = 0.dp,
                         modifier = Modifier
                                 .fillMaxWidth()
                                 .height(bottomBarHeight)) {
            screens.forEach { screen ->
                AddItem(screen = screen ,
                        currentDestination = currentDestination ,
                        navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
        screen: BottomBarHomeItem,
        currentDestination: NavDestination?,
        navController: NavHostController
                    ) {
    BottomNavigationItem(
            label = { Text(text = screen.title ,
                           fontFamily = raleway ,
                           textAlign = TextAlign.Center ,
                           fontSize = 12.sp ,
                           fontWeight = FontWeight.SemiBold ,
                           softWrap = false) } ,
            icon = { Icon(imageVector = screen.icon , contentDescription = "") } ,
            selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true ,
            unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f) ,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            modifier = Modifier.navigationBarsPadding()
    )
}