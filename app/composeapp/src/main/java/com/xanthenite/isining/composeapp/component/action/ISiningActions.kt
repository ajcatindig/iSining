package com.xanthenite.isining.composeapp.component.action

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.R

@Composable
fun ThemeSwitchAction(onToggle: () -> Unit) {
    val icon = painterResource(R.drawable.ic_day)
    IconButton(onClick = onToggle) {
        Icon(icon,
             "Theme switch",
             Modifier
                .padding(8.dp)
        )
    }
}

