package com.xanthenite.isining.composeapp.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.dialog.FailureDialog
import com.xanthenite.isining.composeapp.component.dialog.LoaderDialog
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.composeapp.utils.ISiningPreview
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.view.viewmodel.auth.TwoFactorViewModel

@Composable
fun TwoFactorAuthScreen(
    viewModel: TwoFactorViewModel,
    onNavigateToHome : () -> Unit,
    onNavigateUp : () -> Unit
)
{
    val state by viewModel.collectState()

    TwoFactorContent(
        isLoading = state.isLoading,
        error = state.error,
        verificationCode = state.verification_code,
        onVerificationCodeChange = viewModel::setCode,
        onSubmitClick = viewModel::authenticate,
        onBackClick = onNavigateUp)

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onNavigateToHome()
        }
    }
}

@Composable
fun TwoFactorContent(
        isLoading : Boolean ,
        error : String? ,
        verificationCode : String ,
        onVerificationCodeChange : (String) -> Unit ,
        onSubmitClick : () -> Unit ,
        onBackClick : () -> Unit)
{
    if (isLoading) {
        LoaderDialog()
    }

    if (error != null) {
        FailureDialog(error)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.surface)
        .verticalScroll(rememberScrollState()))
    {
        TopMessage2fa()
        TwoFactorForm(
                verificationCode =  verificationCode,
                onVerificationCodeChange = onVerificationCodeChange,
                onSubmitClick = onSubmitClick
        )
    }
}

@Composable
fun TwoFactorForm(
        verificationCode : String,
        onVerificationCodeChange : (String) -> Unit,
        onSubmitClick : () -> Unit)
{
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf { verificationCode.isNotBlank() }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally)
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                //Verification code
                OutlinedTextField(
                        value = verificationCode ,
                        onValueChange = onVerificationCodeChange ,
                        label = { Text(text = "Enter 6-digit verification code") } ,
                        modifier = Modifier
                                .padding(horizontal = 16.dp , vertical = 8.dp),
                        leadingIcon = { Icon(Icons.Outlined.Security , "") } ,
                        textStyle = TextStyle(color = MaterialTheme.colors.onPrimary , fontSize = 16.sp) ,
                        shape = RoundedCornerShape(10.dp) ,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number , imeAction = ImeAction.Done) ,
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }) ,
                        singleLine = true)
            }
        }
        Column(modifier = Modifier
                .fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                //Button
                Button(
                        onClick = onSubmitClick ,
                        enabled = isValidate ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(85.dp)
                            .padding(vertical = 16.dp, horizontal = 16.dp) ,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) ,
                        shape = RoundedCornerShape(25.dp))
                {
                    Text(text = "Submit" , color = Color.White , style = typography.h6)
                }
            }
        }
    }
}

@Composable
fun TopMessage2fa()
{
    Column(Modifier.fillMaxWidth())
    {
        Image(painter = painterResource(id = R.drawable.two_factor) ,
              contentDescription = "" ,
              modifier = Modifier
                  .requiredSize(200.dp)
                  .align(Alignment.CenterHorizontally))
    }
    Text(text = "Two-Factor Authentication" ,
         style = typography.h4 ,
         modifier = Modifier
             .padding(horizontal = 16.dp, vertical = 16.dp)
             .fillMaxWidth() ,
         textAlign = TextAlign.Center)
    Text(text = "To provide extra layer of security, we require Two-Factor Authentication everytime you login. " +
                "Please open your Google Authenticator to access your 6-digit verification code.",
         style = typography.subtitle1 ,
         modifier = Modifier
             .padding(horizontal = 16.dp, vertical = 16.dp)
             .fillMaxWidth() ,
         textAlign = TextAlign.Center)
}

@Composable
fun BackToLoginScreen(modifier : Modifier, onBackClick : () -> Unit)
{
    IconButton(onClick = onBackClick, modifier.padding(horizontal = 8.dp, vertical = 20.dp))
    {
        Icon(Icons.Outlined.ArrowBackIos , contentDescription = "" ,
             tint = MaterialTheme.colors.onPrimary)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2FAScreen() = ISiningPreview {
    TwoFactorContent(
            isLoading = false,
            error = null,
            verificationCode =  "123-456",
            onVerificationCodeChange = {},
            onSubmitClick = {},
            onBackClick = {})
}