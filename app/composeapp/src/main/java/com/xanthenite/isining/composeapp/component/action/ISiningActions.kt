package com.xanthenite.isining.composeapp.component.action

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.LightbulbCircle
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.R

@Composable
fun ThemeSwitchAction(onToggle: () -> Unit) {
    IconButton(onClick = onToggle) {
        Icon(Icons.Outlined.Lightbulb ,
             contentDescription = "Theme switch" ,
             Modifier.padding(8.dp),
             tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun ArScanAction(onClick : () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Outlined.QrCodeScanner,
             contentDescription = "AR Scanner",
             Modifier.padding(8.dp),
             tint = MaterialTheme.colors.onPrimary
        )
    }
}

