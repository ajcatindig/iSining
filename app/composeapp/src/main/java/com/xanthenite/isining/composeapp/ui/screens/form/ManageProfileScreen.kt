package com.xanthenite.isining.composeapp.ui.screens.form

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.dialog.FailureDialog
import com.xanthenite.isining.composeapp.component.dialog.LoaderDialog
import com.xanthenite.isining.composeapp.component.dialog.SuccessDialog
import com.xanthenite.isining.composeapp.ui.theme.lightBlue
import com.xanthenite.isining.composeapp.ui.theme.offWhite
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.view.viewmodel.form.UpdateProfileViewModel

@Composable
fun ManageProfileScreen(
        onNavigateUp : () -> Unit,
        viewModel : UpdateProfileViewModel)
{
    val state by viewModel.collectState()

    ManageProfileContent(
            isLoading =  state.isLoading,
            isSuccess =  state.isSuccess,
            error =  state.error,
            onNavigateUp = onNavigateUp ,
            onSubmitClick = viewModel::updateProfile ,
            isValidName =  state.isValidUsername ?: true,
            isValidNumber =  state.isValidNumber ?: true,
            isValidAddress =  state.isValidAddress ?: true,
            name =  state.name,
            onNameChange =  viewModel::setName,
            number =  state.mobile_number,
            onNumberChange =  viewModel::setMobileNumber,
            address =  state.address,
            onAddressChange =  viewModel::setAddress,
            bio =  state.bio,
            onBioChange =  viewModel::setBio)
}

@Composable
fun ManageProfileContent(
        isLoading : Boolean,
        isSuccess : String?,
        error : String?,
        onNavigateUp : () -> Unit ,
        onSubmitClick : () -> Unit ,
        isValidName : Boolean,
        isValidNumber : Boolean,
        isValidAddress : Boolean,
        name : String,
        onNameChange : (String) -> Unit,
        number : String,
        onNumberChange : (String) -> Unit,
        address : String,
        onAddressChange : (String) -> Unit,
        bio : String,
        onBioChange : (String) -> Unit)
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
        BackToProfile(Modifier.align(Alignment.Start), onBackClick = onNavigateUp)
        TopMessageUpdate()

        ManageProfileForm(
                name = name ,
                onNameChange =  onNameChange,
                number =  number,
                onNumberChange = onNumberChange,
                address =  address,
                onAddressChange = onAddressChange,
                bio =  bio,
                onBioChange = onBioChange,
                onUpdateClick = onSubmitClick ,
                isValidName =  isValidName,
                isValidNumber =  isValidNumber,
                isValidAddress = isValidAddress)
    }
}

@Composable
fun ManageProfileForm(
        name : String,
        onNameChange : (String) -> Unit,
        number : String,
        onNumberChange : (String) -> Unit,
        address : String,
        onAddressChange : (String) -> Unit,
        bio : String,
        onBioChange : (String) -> Unit,
        onUpdateClick : () -> Unit,
        isValidName : Boolean,
        isValidNumber : Boolean,
        isValidAddress : Boolean)
{
    val helperText = ""
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf { name.isNotBlank() && number.isNotBlank() && address.isNotBlank() && bio.isNotBlank() }


    //Profile Photo
    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp , end = 16.dp , top = 20.dp , bottom = 16.dp),
           horizontalAlignment = Alignment.Start,
           verticalArrangement = Arrangement.Center)
    {
        //Username
        OutlinedTextField(value = name ,
                          onValueChange = onNameChange ,
                          modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(vertical = 8.dp),
                          label = { Text(text = "Username") } ,
                          leadingIcon = { Icon(Icons.Outlined.Person , "") } ,
                          textStyle = TextStyle(
                                  color = MaterialTheme.colors.onPrimary ,
                                  fontSize = 16.sp) ,
                          shape = RoundedCornerShape(10.dp) ,
                          keyboardOptions = KeyboardOptions(
                                  keyboardType = KeyboardType.Text ,
                                  imeAction = ImeAction.Next) ,
                          keyboardActions = KeyboardActions(onNext = {
                              focusManager.moveFocus(focusDirection = FocusDirection.Down)
                          }) ,
                          singleLine = true,
                          isError = !isValidName)
        if (helperText.isEmpty()) {
            Spacer(modifier = Modifier.padding(1.dp))
            Text(stringResource(id = R.string.message_field_username_invalid) ,
                 style = MaterialTheme.typography.caption , fontStyle = FontStyle.Italic, textAlign = TextAlign.Start)
        }

        //Mobile Number
        OutlinedTextField(value = number ,
                          onValueChange = onNumberChange ,
                          modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(vertical = 8.dp),
                          label = { Text(text = "Mobile Number") } ,
                          leadingIcon = { Icon(Icons.Outlined.ContactPhone , "") } ,
                          textStyle = TextStyle(
                                  color = MaterialTheme.colors.onPrimary ,
                                  fontSize = 16.sp) ,
                          shape = RoundedCornerShape(10.dp) ,
                          keyboardOptions = KeyboardOptions(
                                  keyboardType = KeyboardType.Number ,
                                  imeAction = ImeAction.Next
                                                           ) ,
                          keyboardActions = KeyboardActions(onNext = {
                              focusManager.moveFocus(focusDirection = FocusDirection.Down)
                          }) ,
                          singleLine = true,
                          isError = !isValidNumber)
        if (helperText.isEmpty()) {
            Spacer(modifier = Modifier.padding(1.dp))
            Text(stringResource(id = R.string.message_number_invalid) ,
                 style = MaterialTheme.typography.caption , fontStyle = FontStyle.Italic, textAlign = TextAlign.Start)
        }

        //Address
        OutlinedTextField(value = address ,
                          onValueChange = onAddressChange ,
                          modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(vertical = 8.dp),
                          label = { Text(text = "Address") } ,
                          leadingIcon = { Icon(Icons.Outlined.LocationCity , "") } ,
                          textStyle = TextStyle(
                                  color = MaterialTheme.colors.onPrimary ,
                                  fontSize = 16.sp) ,
                          shape = RoundedCornerShape(10.dp) ,
                          keyboardOptions = KeyboardOptions(
                                  keyboardType = KeyboardType.Text ,
                                  imeAction = ImeAction.Next
                                                           ) ,
                          keyboardActions = KeyboardActions(onNext = {
                              focusManager.moveFocus(focusDirection = FocusDirection.Down)
                          }) ,
                          maxLines = 5,
                          isError = !isValidAddress)
        if (helperText.isEmpty()) {
            Spacer(modifier = Modifier.padding(1.dp))
            Text(stringResource(id = R.string.message_address_invalid) ,
                 style = MaterialTheme.typography.caption , fontStyle = FontStyle.Italic, textAlign = TextAlign.Start)
        }

        //Bio
        OutlinedTextField(value = bio ,
                          onValueChange = onBioChange ,
                          modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(vertical = 8.dp),
                          label = { Text(text = "Write something about yourself") } ,
                          leadingIcon = { Icon(Icons.Outlined.NoteAlt , "") } ,
                          textStyle = TextStyle(
                                  color = MaterialTheme.colors.onPrimary ,
                                  fontSize = 16.sp) ,
                          shape = RoundedCornerShape(10.dp) ,
                          keyboardOptions = KeyboardOptions(
                                  keyboardType = KeyboardType.Text ,
                                  imeAction = ImeAction.Done) ,
                          keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })  ,
                          maxLines = 5)

        Button(onClick = { onUpdateClick() } ,
               enabled = isValidate ,
               modifier = Modifier
                       .fillMaxWidth()
                       .padding(vertical = 16.dp)
                       .height(50.dp) ,
               colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) ,
               shape = RoundedCornerShape(10.dp))
        {
            Text(text = "Save Changes" , color = Color.White , style = typography.h6)
        }
    }
}


@Composable
fun BackToProfile(modifier : Modifier , onBackClick : () -> Unit)
{
    IconButton(onClick = onBackClick, modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 20.dp))
    {
        Icon(Icons.Outlined.ArrowBackIos , contentDescription = "" ,
             tint = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun TopMessageUpdate()
{
    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) ,
           horizontalAlignment = Alignment.CenterHorizontally ,
           verticalArrangement = Arrangement.Center)
    {
        Text(text = "Update Profile",
             style = MaterialTheme.typography.h5,
             fontSize = 25.sp,
             textAlign = TextAlign.Center)
    }
    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center)
    {
        Text(text = "Update your basic profile information.",
             style = MaterialTheme.typography.subtitle1,
             fontSize = 18.sp,
             textAlign = TextAlign.Center)
    }
}