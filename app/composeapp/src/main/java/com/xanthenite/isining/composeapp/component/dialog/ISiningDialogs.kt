package com.xanthenite.isining.composeapp.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
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


@Composable
fun SuccessDialog(successMessage : String,
                  onDismissed: () -> Unit = {})
{
    val isDismissed = remember { mutableStateOf(false) }

    if (!isDismissed.value) {
        Dialog(onDismissRequest = { })
        {
            Surface{
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    LottieAnimation(
                            resId = R.raw.success,
                            modifier = Modifier
                                    .padding(16.dp)
                                    .size(84.dp)
                                   )
                    Text(
                            text = successMessage,
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
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

@Composable
fun ConfirmationDialog(
        title: String,
        message: String,
        onConfirmedYes: () -> Unit,
        onConfirmedNo: () -> Unit,
        onDismissed: () -> Unit
                      ) {

    var isDismissed by remember { mutableStateOf(false) }

    if (!isDismissed) {
        AlertDialog(
            modifier = Modifier
                    .fillMaxWidth(),
            onDismissRequest = onDismissed,
            title = {
                Text(text = title)
            },
            text = {
                Text(
                    text = message,
                    fontSize = 15.sp,)
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    TextButton(
                        onClick = {
                            onConfirmedYes()
                            isDismissed = true
                        },
                        modifier = Modifier
                                .padding(4.dp),)
                    {
                        Text(
                            text = "Yes",
                            style = MaterialTheme.typography.button.copy(
                                    fontWeight = FontWeight.Medium))
                    }
                    TextButton(
                        onClick = {
                            onConfirmedNo()
                            isDismissed = true
                        },
                        modifier = Modifier
                                .padding(4.dp),)
                    {
                        Text(
                            text = "No",
                            style = MaterialTheme.typography.button.copy(
                                    fontWeight = FontWeight.Medium))
                    }
                }
            },
        )
    }
}

