package com.xanthenite.isining.composeapp.component.scaffold.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Artist usable Top app bar for the project
 */
@Composable
fun ArtistTopBar(
        title : String = "Gallery Artists",
        actions : @Composable() (RowScope.() -> Unit) = {})
{
    TopAppBar(
            title = {
                Row {
                    Text(text = title ,
                         textAlign = TextAlign.Start ,
                         color = MaterialTheme.colors.onPrimary ,
                         modifier = Modifier.fillMaxWidth() ,
                         style = MaterialTheme.typography.h5)
                }
            } ,
            actions = actions ,
            backgroundColor = MaterialTheme.colors.surface ,
            contentColor = MaterialTheme.colors.onPrimary ,
            elevation = 0.dp)
}