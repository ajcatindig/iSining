package com.xanthenite.isining.composeapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xanthenite.isining.composeapp.component.bottombar.BottomBarHomeItem
import com.xanthenite.isining.composeapp.ui.screens.detail.AboutScreen
import com.xanthenite.isining.composeapp.ui.screens.detail.ArtistDetailScreen
import com.xanthenite.isining.composeapp.ui.screens.detail.ArtworkDetailScreen
import com.xanthenite.isining.composeapp.ui.screens.detail.ExhibitDetailScreen
import com.xanthenite.isining.composeapp.ui.screens.form.HireArtistScreen
import com.xanthenite.isining.composeapp.ui.screens.form.OfferFormScreen
import com.xanthenite.isining.composeapp.ui.screens.home.*
import com.xanthenite.isining.composeapp.utils.assistedViewModel
import com.xanthenite.isining.view.viewmodel.detail.ArtistDetailViewModel
import com.xanthenite.isining.view.viewmodel.detail.ArtworkDetailViewModel
import com.xanthenite.isining.view.viewmodel.detail.ExhibitDetailViewModel
import java.text.Normalizer.Form

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

        composable(route = BottomBarHomeItem.Exhibits.route)
        {
            ExhibitScreen(
                viewModel = hiltViewModel(),
                viewModel1 = hiltViewModel(),
                viewModel2 = hiltViewModel(),
                viewModel3 = hiltViewModel(),
                onNavigateToExhibitDetail = { navController.navigateToExhibitDetail(it) }
            )
        }

        composable(route = BottomBarHomeItem.Artworks.route)
        {
            ArtworkScreen(
                viewModel = hiltViewModel(),
                onNavigateToArtworkDetail = { navController.navigateToArtworkDetail(it) }
            )
        }

        composable(route = BottomBarHomeItem.Artists.route)
        {
            ArtistScreen(
                viewModel = hiltViewModel(),
                onNavigateToArtistDetail = { navController.navigateToArtistDetail(it) }
            )
        }

        composable(route = BottomBarHomeItem.Profile.route)
        {
            ProfileScreen(
                viewModel = hiltViewModel(),
                onNavigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                onAboutAppCLick = { navController.navigateToAboutApp() },
                onTransactionClick = {},
                onManageProfileClick = {},
                onChangePasswordClick = {}
            )
        }

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
                onNavigateToArtworkDetail = { it1 -> navController.navigateToArtworkDetail(it1) })
        }

        composable(
            DetailScreen.Artwork.route,
            arguments = listOf(navArgument("id"){type = NavType.IntType}))
        {
            val artworkId = requireNotNull(it.arguments?.getInt(DetailScreen.Artwork.ARG_ARTWORK_ID))
            ArtworkDetailScreen(
                onNavigateUp = { navController.navigateUp() } ,
                viewModel =  assistedViewModel {
                    ArtworkDetailViewModel.provideFactory(artworkDetailViewModelFactory(), artworkId)
                },
                onNavigateToArtist = { it1 -> navController.navigateToArtistDetail(it1) },
                onMakeOffer = { it1 -> navController.navigateToOfferForm(it1) })
        }

        composable(
            DetailScreen.Artist.route,
            arguments = listOf(navArgument("id"){type = NavType.IntType}))
        {
            val artistId = requireNotNull(it.arguments?.getInt(DetailScreen.Artist.ARG_ARTIST_ID))
            ArtistDetailScreen(
                onNavigateUp = { navController.navigateUp() } ,
                viewModel =  assistedViewModel {
                    ArtistDetailViewModel.provideFactory(artistDetailViewModelFactory(), artistId)
                },
                onNavigateToArtwork = { it1 -> navController.navigateToArtworkDetail(it1) },
                onHireArtist = { it1 -> navController.navigateToCommissionForm(it1) })
        }

        composable(
            FormScreen.Offer.route,
            arguments = listOf(navArgument("id"){type = NavType.IntType}))
        {
            val artworkId = requireNotNull(it.arguments?.getInt(FormScreen.Offer.ARG_OFFER_ID))
            OfferFormScreen(
                    onNavigateUp = { navController.navigateUp()} ,
                    viewModel1 =  assistedViewModel {
                        ArtworkDetailViewModel.provideFactory(artworkDetailViewModelFactory(), artworkId)
                    },
                    viewModel2 = hiltViewModel() ,
                    viewModel3 = hiltViewModel())
        }

        composable(
            FormScreen.Commission.route,
            arguments = listOf(navArgument("id"){type = NavType.IntType}))
        {
            val artistId = requireNotNull(it.arguments?.getInt(FormScreen.Commission.ARG_COMMISSION_ID))
            HireArtistScreen(
                    onNavigateUp = { navController.navigateUp() } ,
                    viewModel1 =  assistedViewModel {
                        ArtistDetailViewModel.provideFactory(artistDetailViewModelFactory(), artistId)
                    },
                    viewModel2 = hiltViewModel())
        }

        composable(DetailScreen.About.route) { AboutScreen(onNavigateUp = { navController.navigateUp() }) }

        authNavGraph(navController)
    }
}

/**
 * Launches note detail screen for specified [exhibitId]
 */
fun NavController.navigateToExhibitDetail(id : Int) = navigate(DetailScreen.Exhibit.route(id))

/**
 * Launches artwork detail screen for specified [artworkId]
 */
fun NavController.navigateToArtworkDetail(id : Int) = navigate(DetailScreen.Artwork.route(id))

/**
 * Launches artist detail screen for specified [artistId]
 */
fun NavController.navigateToArtistDetail(id : Int) = navigate(DetailScreen.Artist.route(id))

/**
 * Launches about app
 */
fun NavController.navigateToAboutApp() = navigate(DetailScreen.About.route)

/**
 * Launches offer form screen for specified [artworkId]
 */
fun NavController.navigateToOfferForm(id : Int) = navigate(FormScreen.Offer.route(id))

/**
 * Launches commission form screen for specified [artistId]
 */
fun NavController.navigateToCommissionForm(id : Int) = navigate(FormScreen.Commission.route(id))

/**
 * Sealed class for detail screens
 */
sealed class DetailScreen(val route : String, val name : String)
{
    object Exhibit : DetailScreen("exhibit/{id}", "Exhibit Details") {
        fun route(id : Int) = "exhibit/$id"

        const val ARG_EXHIBIT_ID : String = "id"
    }
    object Artwork : DetailScreen("artwork/{id}", "Artwork Details") {
        fun route(id : Int) = "artwork/$id"

        const val ARG_ARTWORK_ID : String = "id"
    }
    object Artist : DetailScreen("artist/{id}", "Artist Details") {
        fun route(id : Int) = "artist/$id"

        const val ARG_ARTIST_ID : String = "id"
    }
    object About : DetailScreen("about", "About App")
}

/**
 * Sealed class for forms
 */
sealed class FormScreen(val route : String, val name : String)
{
    object Offer : FormScreen("offer/{id}", "Offer Form") {
        fun route(id : Int) = "offer/$id"

        const val ARG_OFFER_ID : String = "id"
    }
    object Commission : FormScreen("commission/{id}", "Commission Form") {
        fun route(id : Int) = "commission/$id"

        const val ARG_COMMISSION_ID : String = "id"
    }
}



