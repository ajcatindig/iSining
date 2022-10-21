package com.xanthenite.isining.composeapp.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.BuildConfig
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.composeapp.component.scaffold.detail.AboutAppTopbar
import com.xanthenite.isining.composeapp.ui.theme.lightBlue
import com.xanthenite.isining.composeapp.ui.theme.lightGray
import com.xanthenite.isining.composeapp.utils.IntentUtils

@Composable
fun AboutScreen(onNavigateUp : () -> Unit) {
    ISiningScaffold(
        topAppBar = {
            AboutAppTopbar(onNavigateUp = onNavigateUp)
        },
        content = {
            AboutContent()
        }
    )
}

@Composable
fun AboutContent() {
    Surface(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface))
    {
        LazyColumn {
            item {
                Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp))
                {
                    //App logo
                    Spacer(modifier = Modifier.height(60.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            val image = painterResource(id = R.drawable.app_logo)
                            Image(painter = image ,
                                  contentDescription = "About App",
                                  modifier = Modifier.size(92.dp, 92.dp),
                                  alignment = Alignment.Center)
                        }
                    }
                    //App name
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(text = "i-Sining",
                                 textAlign = TextAlign.Center,
                                 fontSize = 20.sp,
                                 style = MaterialTheme.typography.h5,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    //App version
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                                 textAlign = TextAlign.Center,
                                 fontSize = 18.sp,
                                 style = MaterialTheme.typography.subtitle1,
                                 color = MaterialTheme.colors.onPrimary)
                        }
                    }
                    //Terms and Privacy
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(modifier = Modifier
                            .fillMaxWidth(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center)
                    {
                        Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center)
                        {
                            VisitCard()
                        }
                        Row(modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center)
                        {
                            TermsAndService()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VisitCard() {
    val context = LocalContext.current
    val visitUrl = stringResource(id = R.string.visitUrl)
    Card(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { IntentUtils.launchBrowser(context , visitUrl) } ,
         backgroundColor = lightGray ,
         elevation = 0.dp ,
         shape = RoundedCornerShape(10.dp))
    {
        Column(modifier = Modifier
                .fillMaxWidth(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp , start = 16.dp , end = 16.dp , bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Visit our website",
                     style = MaterialTheme.typography.h5,
                     fontSize = 20.sp,
                     textAlign = TextAlign.Start,
                     color = Color.Black)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp , start = 16.dp , end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = stringResource(id = R.string.visitUrl),
                     style = MaterialTheme.typography.subtitle2,
                     fontSize = 16.sp,
                     textAlign = TextAlign.Start,
                     color = lightBlue,
                     textDecoration = TextDecoration.Underline)
            }
        }
    }
}

@Composable
fun TermsAndService() {
    val context = LocalContext.current
    val termsUrl = stringResource(id = R.string.terms_privacy)
    Card(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { IntentUtils.launchBrowser(context , termsUrl) } ,
         backgroundColor = lightGray ,
         elevation = 0.dp ,
         shape = RoundedCornerShape(10.dp))
    {
        Column(modifier = Modifier
                .fillMaxWidth(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp , start = 16.dp , end = 16.dp , bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Terms and Privacy",
                     style = MaterialTheme.typography.h5,
                     fontSize = 20.sp,
                     textAlign = TextAlign.Start,
                     color = Color.Black)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp , start = 16.dp , end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = stringResource(id = R.string.terms_privacy),
                     style = MaterialTheme.typography.subtitle2,
                     fontSize = 16.sp,
                     textAlign = TextAlign.Start,
                     color = lightBlue,
                     textDecoration = TextDecoration.Underline)
            }
        }
    }
}