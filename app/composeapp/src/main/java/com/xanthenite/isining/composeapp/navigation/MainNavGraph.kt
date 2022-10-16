package com.xanthenite.isining.composeapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xanthenite.isining.composeapp.component.bottombar.BottomBarHomeItem
import com.xanthenite.isining.composeapp.ui.screens.detail.ExhibitDetailScreen
import com.xanthenite.isining.composeapp.ui.screens.home.*
import com.xanthenite.isining.composeapp.utils.assistedViewModel
import com.xanthenite.isining.view.viewmodel.detail.ExhibitDetailViewModel

@Composable
fun MainNavGraph(navController : NavHostController)
{
    NavHost(
        navController = navController ,
        route = Graph.HOME,
        startDestination = BottomBarHomeItem.Home.route) {

        composable(route = BottomBarHomeItem.Home.route)
        {
            HomeScreen(
                viewModel = hiltViewModel(),
                onNavigateToAr = {}
            )
        }

        composable(route = BottomBarHomeItem.Exhibits.route) {
            ExhibitScreen(
                viewModel = hiltViewModel(),
                onNavigateToExhibitDetail = { navController.navigateToExhibitDetail(it) }
            )
        }

        composable(route = BottomBarHomeItem.Artworks.route) {
            ArtworkScreen(
                viewModel = hiltViewModel(),
                onNavigateToArtworkDetail = {/*TODO:Implement navigation to artwork detail screen*/}
            )
        }

        composable(route = BottomBarHomeItem.Artists.route) {
            ArtistScreen(
                viewModel = hiltViewModel(),
                onNavigateToArtistDetail = {/*TODO:Implement navigation to artist detail screen*/}
            )
        }

        composable(route = BottomBarHomeItem.Profile.route) {
            ProfileScreen(
                viewModel = hiltViewModel(),
                onNavigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                onAboutAppCLick = {},
                onTransactionClick = {},
                onManageProfileClick = {},
                onChangePasswordClick = {}
            )
        }

        authNavGraph(navController)

        composable(
            DetailScreen.Exhibit.route,
            arguments = listOf(navArgument("id"){ type = NavType.IntType }))
        {
            val exhibitId = requireNotNull(it.arguments?.getInt(DetailScreen.Exhibit.ARG_EXHIBIT_ID))
            ExhibitDetailScreen(
                onNavigateUp = { navController.navigateUp() } ,
                viewModel =  assistedViewModel {
                    ExhibitDetailViewModel.provideFactory(exhibitDetailViewModelFactory(), exhibitId)
                },
                onNavigateToArtworkDetail = {})
        }
    }
}

/**
 * Launches note detail screen for specified [exhibitId]
 */
fun NavController.navigateToExhibitDetail(id : Int) = navigate(DetailScreen.Exhibit.route(id))

sealed class DetailScreen(val route : String, val name : String) {
    object Exhibit : DetailScreen("exhibit/{id}", "Exhibit Details") {
        fun route(id : Int) = "exhibit/$id"

        const val ARG_EXHIBIT_ID : String = "id"
    }
}