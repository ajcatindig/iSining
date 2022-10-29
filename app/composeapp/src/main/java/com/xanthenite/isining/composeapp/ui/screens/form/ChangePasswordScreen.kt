package com.xanthenite.isining.composeapp.ui.screens.form

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.dialog.FailureDialog
import com.xanthenite.isining.composeapp.component.dialog.LoaderDialog
import com.xanthenite.isining.composeapp.component.dialog.SuccessDialog
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.view.viewmodel.form.ChangePassViewModel

@Composable
fun ChangePasswordScreen(
        onNavigateUp : () -> Unit,
        viewModel : ChangePassViewModel)
{
    val state by viewModel.collectState()

    ChangePasswordContent(
            isLoading = state.isLoading ,
            isSuccess =  state.isSuccess,
            error =  state.error,
            onNavigateUp = onNavigateUp ,
            onSubmitClick = viewModel::changePassword ,
            currentPassword =  state.currentPassword,
            onCurrentPassChange =  viewModel::setCurrentPassword,
            newPassword =  state.newPassword,
            onNewPassChange =  viewModel::setNewPassword,
            confirmPassword =  state.confirmPassword,
            onConfirmPassChange =  viewModel::setConfirmPassword,
            isValidNewPass =  state.isValidNewPass ?: true,
            isValidConfirmPass = state.isValidConfirmPass ?: true)
}

@Composable
fun ChangePasswordContent(
        isLoading : Boolean ,
        isSuccess : String? ,
        error : String? ,
        onNavigateUp : () -> Unit ,
        onSubmitClick : () -> Unit ,
        currentPassword : String ,
        onCurrentPassChange : (String) -> Unit ,
        newPassword : String ,
        onNewPassChange : (String) -> Unit ,
        confirmPassword : String ,
        onConfirmPassChange : (String) -> Unit ,
        isValidNewPass : Boolean ,
        isValidConfirmPass : Boolean)
{
    if (isLoading) {
        LoaderDialog()
    }

    if (error != null) {
        FailureDialog(error)
    }

    if (isSuccess != null) {
        SuccessDialog(isSuccess)
    }

    Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .verticalScroll(rememberScrollState()))
    {
        BackToProf(Modifier.align(Alignment.Start), onBackClick = onNavigateUp)

        TopMessageChangePass()

        ChangePasswordForm(
                currentPassword = currentPassword,
                onCurrentPassChange =  onCurrentPassChange,
                newPassword =  newPassword,
                onNewPassChange =  onNewPassChange,
                confirmPassword =  confirmPassword,
                onConfirmPassChange =  onConfirmPassChange,
                onSubmitClick = onSubmitClick ,
                isValidNewPass =  isValidNewPass,
                isValidConfirmPass = isValidConfirmPass)
    }
}

@Composable
fun ChangePasswordForm(
        currentPassword : String,
        onCurrentPassChange : (String) -> Unit,
        newPassword : String,
        onNewPassChange : (String) -> Unit,
        confirmPassword : String,
        onConfirmPassChange : (String) -> Unit,
        onSubmitClick : () -> Unit,
        isValidNewPass : Boolean,
        isValidConfirmPass : Boolean)
{
    var isPasswordVisible by remember { mutableStateOf(false) }
    val helperText = ""
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf { currentPassword.isNotBlank() && newPassword.isNotBlank() && confirmPassword.isNotBlank() }

    Column(modifier = Modifier
            .fillMaxSize())
    {
        //Current Password
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp , top = 20.dp),
               horizontalAlignment = Alignment.Start,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Enter Current Password",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            OutlinedTextField(value = currentPassword ,
                              onValueChange = onCurrentPassChange ,
                              label = { Text(text = "Current Password") } ,
                              modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(vertical = 8.dp) ,
                              leadingIcon = { Icon(Icons.Outlined.Lock , "") } ,
                              textStyle = TextStyle(
                                      color = MaterialTheme.colors.onPrimary ,
                                      fontSize = 16.sp) ,
                              shape = RoundedCornerShape(10.dp) ,
                              keyboardOptions = KeyboardOptions(
                                      keyboardType = KeyboardType.Password ,
                                      imeAction = ImeAction.Next) ,
                              keyboardActions = KeyboardActions(onNext = {
                                  focusManager.moveFocus(focusDirection = FocusDirection.Down)
                              }) ,
                              visualTransformation = PasswordVisualTransformation(),
                              singleLine = true)
        }
        //New Password
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
               horizontalAlignment = Alignment.Start,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Enter New Password",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            OutlinedTextField(value = newPassword ,
                              onValueChange = onNewPassChange ,
                              label = { Text(text = "New Password") } ,
                              modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(vertical = 8.dp) ,
                              leadingIcon = { Icon(Icons.Outlined.Lock , "") } ,
                              textStyle = TextStyle(
                                      color = MaterialTheme.colors.onPrimary ,
                                      fontSize = 16.sp
                                                   ) ,
                              shape = RoundedCornerShape(10.dp) ,
                              keyboardOptions = KeyboardOptions(
                                      keyboardType = KeyboardType.Password ,
                                      imeAction = ImeAction.Next) ,
                              keyboardActions = KeyboardActions(onNext = {
                                  focusManager.moveFocus(focusDirection = FocusDirection.Down)
                              }) ,
                              visualTransformation = if (isPasswordVisible) VisualTransformation.None
                              else PasswordVisualTransformation() ,
                              trailingIcon = {
                                  IconButton(onClick = {
                                      isPasswordVisible = ! isPasswordVisible
                                  }) {
                                      Icon(imageVector = if (isPasswordVisible) Icons.Outlined.Visibility
                                      else Icons.Outlined.VisibilityOff ,
                                           contentDescription = "")
                                  } } ,
                              singleLine = true ,
                              isError = !isValidNewPass)
            if (helperText.isEmpty()) {
                Spacer(modifier = Modifier.padding(1.dp))
                Text(stringResource(id = R.string.message_field_password_invalid),
                     style = MaterialTheme.typography.caption, fontStyle = FontStyle.Italic) }
        }
        //Confirm New Password
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
               horizontalAlignment = Alignment.Start,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Confirm New Password",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            OutlinedTextField(value = confirmPassword ,
                              onValueChange = onConfirmPassChange,
                              label = { Text(text = "Confirm Password") },
                              modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(vertical = 8.dp) ,
                              leadingIcon = { Icon(Icons.Outlined.Password , contentDescription = "") },
                              textStyle = TextStyle(
                                      color = MaterialTheme.colors.onPrimary ,
                                      fontSize = 16.sp
                                                   ) ,
                              keyboardOptions = KeyboardOptions(
                                      keyboardType = KeyboardType.Password,
                                      imeAction = ImeAction.Done) ,
                              keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                              visualTransformation = PasswordVisualTransformation(),
                              singleLine = true,
                              isError = !isValidConfirmPass
                             )
            if (helperText.isNotEmpty()) {
                Spacer(modifier = Modifier.padding(1.dp))
                Text(stringResource(id = R.string.message_password_mismatched),
                     style = MaterialTheme.typography.caption, fontStyle = FontStyle.Italic) }

        }
        /** [BUTTON]*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {

                Button(onClick = onSubmitClick ,
                       enabled = isValidate ,
                       modifier = Modifier
                               .fillMaxWidth()
                               .height(50.dp) ,
                       colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) ,
                       shape = RoundedCornerShape(10.dp))
                {
                    Text(text = "Save Changes" , color = Color.White , style = typography.h6)
                }
            }
        }
    }
}

@Composable
fun BackToProf(modifier : Modifier , onBackClick : () -> Unit)
{
    IconButton(onClick = onBackClick, modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 20.dp))
    {
        Icon(Icons.Outlined.ArrowBackIos , contentDescription = "" ,
             tint = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun TopMessageChangePass()
{
    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) ,
           horizontalAlignment = Alignment.CenterHorizontally ,
           verticalArrangement = Arrangement.Center)
    {
        Text(text = "Change Password" ,
             style = MaterialTheme.typography.h5 ,
             fontSize = 25.sp ,
             textAlign = TextAlign.Center)
    }
    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center)
    {
        Text(text = "It is a good practice to update your password every three months to keep " +
                    "your account secured.",
             style = MaterialTheme.typography.subtitle1,
             fontSize = 18.sp,
             textAlign = TextAlign.Center)
    }
}