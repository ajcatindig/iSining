package com.xanthenite.isining.composeapp.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.xanthenite.isining.R
import com.xanthenite.isining.composeapp.component.anim.LottieAnimation
import com.xanthenite.isining.composeapp.ui.theme.typography

@Composable
fun LoaderDialog() {
    Dialog(onDismissRequest = {}) {
        Surface(modifier = Modifier.size(128.dp)) {
            LottieAnimation(
                    resId = R.raw.loading,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(100.dp))
        }
    }
}

@Composable
fun FailureDialog(failureMessage : String, onDismissed: () -> Unit = {})
{
    val isDismissed = remember { mutableStateOf(false) }

    if (!isDismissed.value) {
        Dialog(onDismissRequest = { })
        {
            Surface{
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    LottieAnimation(
                            resId = R.raw.failure,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(84.dp)
                    )
                    Text(
                        text = failureMessage,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Button(onClick = { isDismissed.value = true },
                           modifier = Modifier
                               .fillMaxWidth()
                               .height(80.dp)
                               .padding(16.dp))
                    {
                        Text(style = typography.subtitle1, color = Color.White, text = "OK")
                    }
                }
            }
        }
    }
}

