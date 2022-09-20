package com.xanthenite.isining.composeapp.ui.screens.auth

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.R
import com.xanthenite.isining.composeapp.component.dialog.FailureDialog
import com.xanthenite.isining.composeapp.component.dialog.LoaderDialog
import com.xanthenite.isining.composeapp.utils.ISiningPreview
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.view.viewmodel.auth.LoginViewModel

@Composable
fun LoginScreen(
        viewModel : LoginViewModel ,
        onNavigateToSignUp : () -> Unit ,
        onNavigateToForgot : () -> Unit ,
        onNavigateToHome : () -> Unit
)
{
    val state by viewModel.collectState()

    LoginContent(
            isLoading = state.isLoading,
            email =  state.email,
            password =  state.password,
            onEmailChange =  viewModel::setEmail,
            onPasswordChange =  viewModel::setPassword,
            onLoginClick = viewModel::login ,
            onSignUpClick = onNavigateToSignUp ,
            onForgotClick = onNavigateToForgot,
            error = state.error)

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onNavigateToHome()
        }
    }
}

@Composable
fun LoginContent(
        isLoading : Boolean ,
        email : String ,
        password : String ,
        onEmailChange : (String) -> Unit ,
        onPasswordChange : (String) -> Unit ,
        onLoginClick : () -> Unit ,
        onSignUpClick : () -> Unit ,
        onForgotClick : () -> Unit,
        error : String?)
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
        TopGreeting()

        LoginForm(email = email ,
                  password = password ,
                  onEmailChange = onEmailChange ,
                  onPasswordChange = onPasswordChange,
                  onLoginClick = onLoginClick)

        ForgotPasswordLink(Modifier.align(Alignment.CenterHorizontally), onForgotClick = onForgotClick)

        SignUpLink(Modifier.align(Alignment.CenterHorizontally), onSignUpClick = onSignUpClick)
    }
}

@Composable
fun TopGreeting()
{
    Column(Modifier.fillMaxWidth())
    {
        Image(
            painter = painterResource(id = R.drawable.app_logo) ,
            contentDescription = "",
            modifier = Modifier
                    .padding(top = 60.dp)
                    .requiredSize(95.dp)
                    .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillBounds)
    }

    Text(
        text = "Welcome!\nLogin your account",
        style = typography.h4,
        modifier = Modifier
                .padding(horizontal = 16.dp , vertical = 30.dp)
                .fillMaxWidth(),
        textAlign = TextAlign.Center)
}

@Composable
fun LoginForm(
        email : String,
        password : String,
        onEmailChange : (String) -> Unit,
        onPasswordChange : (String) -> Unit,
        onLoginClick : () -> Unit)
{
    val focusManager = LocalFocusManager.current
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isValidate by derivedStateOf { email.isNotBlank() && password.isNotBlank() }

    /** [EMAIL]*/
    OutlinedTextField(
            value = email ,
            onValueChange = onEmailChange,
            label = { Text(text = "Email") },
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp , vertical = 8.dp) ,
            leadingIcon = { Icon(Icons.Outlined.Person, "") },
            textStyle = TextStyle(color = MaterialTheme.colors.onPrimary, fontSize = 16.sp),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Down) }),
            singleLine = true)

    /** [PASSWORD]*/
    OutlinedTextField(
            value = password ,
            onValueChange = onPasswordChange ,
            label = { Text(text = "Password") } ,
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp , vertical = 8.dp) ,
            leadingIcon = { Icon(Icons.Outlined.Lock, "") } ,
            textStyle = TextStyle(color = MaterialTheme.colors.onPrimary, fontSize = 16.sp) ,
            shape = RoundedCornerShape(10.dp) ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done) ,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }) ,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation() ,
            trailingIcon = { IconButton(onClick = { isPasswordVisible = ! isPasswordVisible }) {
                Icon(imageVector = if (isPasswordVisible) Icons.Outlined.Visibility
                else Icons.Outlined.VisibilityOff, contentDescription = "")
            }} ,
            singleLine = true)

    /** [BUTTON]*/
    Button(
        onClick = onLoginClick,
        enabled = isValidate,
        modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(vertical = 16.dp , horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        shape = RoundedCornerShape(25.dp))
    {
        Text(text = "Login", color = Color.White, style = typography.h6)
    }

}


@Composable
fun ForgotPasswordLink(modifier : Modifier, onForgotClick : () -> Unit)
{
    Text(text = buildAnnotatedString {
        append("Forgot Password?")
        addStyle(SpanStyle(color = Color.Blue), start = 0, this.length)
        toAnnotatedString()
    },
         style = typography.subtitle1,
         modifier = modifier
                 .padding(vertical = 16.dp , horizontal = 16.dp)
                 .clickable(onClick = onForgotClick))
}

@Composable
fun SignUpLink(modifier : Modifier, onSignUpClick : () -> Unit)
{
    Text(text = buildAnnotatedString {
        append("Don't have an account? Sign up")
        addStyle(SpanStyle(color = Color.Blue), start = 23, this.length)
        toAnnotatedString()
    },
    style = typography.subtitle1,
    modifier = modifier
            .padding(vertical = 50.dp , horizontal = 16.dp)
            .clickable(onClick = onSignUpClick))
}

@Preview
@Composable
fun PreviewLoginContent() = ISiningPreview {
    LoginContent(
            isLoading = false ,
            email =  "johndoe@email.com",
            password =  "password",
            onEmailChange =  {},
            onPasswordChange =  {},
            onLoginClick = {} ,
            onSignUpClick = {} ,
            onForgotClick = {},
            error = null
    )
}