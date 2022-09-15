package com.xanthenite.isining.composeapp.component.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarHomeItem(
    val route : String,
    val title : String,
    val icon : ImageVector)
{
    object Home : BottomBarHomeItem(
        route = "HOME",
        title = "Home",
        icon = Icons.Filled.Home
    )
    object Exhibits : BottomBarHomeItem(
        route = "EXHIBITS",
        title = "Exhibits",
        icon = Icons.Filled.Dashboard
    )
    object Artworks : BottomBarHomeItem(
        route = "ARTWORKS",
        title = "Artworks",
        icon = Icons.Filled.Palette
    )
    object Artists : BottomBarHomeItem(
        route = "ARTISTS",
        title = "Artists",
        icon = Icons.Filled.PeopleAlt
    )
    object Profile : BottomBarHomeItem(
        route = "PROFILE",
        title = "Profile",
        icon = Icons.Filled.AccountCircle
    )
}