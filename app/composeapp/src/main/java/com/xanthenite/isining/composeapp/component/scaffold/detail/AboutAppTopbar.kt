package com.xanthenite.isining.composeapp.component.scaffold.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AboutAppTopbar(
        title : String = "About App",
        onNavigateUp : (() -> Unit)? = null)
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
            navigationIcon = onNavigateUp?.let {
                val navIcon : @Composable () -> Unit = {
                    IconButton(
                            onClick = onNavigateUp,
                            modifier = Modifier.padding(start = 4.dp)) {
                        Icon(
                                imageVector = Icons.Filled.ArrowBackIos ,
                                contentDescription = "Back" ,
                                tint = MaterialTheme.colors.onPrimary)
                    }
                }
                navIcon
            } ,
            backgroundColor = MaterialTheme.colors.surface ,
            contentColor = MaterialTheme.colors.onPrimary ,
            elevation = 0.dp)
}