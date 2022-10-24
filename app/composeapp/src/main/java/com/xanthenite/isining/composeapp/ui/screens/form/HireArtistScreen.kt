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
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.NoteAlt
import androidx.compose.material.icons.outlined.PriceChange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.component.dialog.FailureDialog
import com.xanthenite.isining.composeapp.component.dialog.LoaderDialog
import com.xanthenite.isining.composeapp.component.dialog.SuccessDialog
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.view.viewmodel.detail.ArtistDetailViewModel
import com.xanthenite.isining.view.viewmodel.form.HireArtistViewModel

@Composable
fun HireArtistScreen(
        onNavigateUp : () -> Unit ,
        viewModel1 : ArtistDetailViewModel ,
        viewModel2 : HireArtistViewModel)
{
    val state1 by viewModel1.collectState()
    val state2 by viewModel2.collectState()

    HireArtistContent(
            price =  state2.price,
            onPriceChange =  viewModel2::setPrice,
            address =  state2.address,
            onAddressChange =  viewModel2::setAddress,
            description =  state2.description,
            onDescriptionChange =  viewModel2::setDescription,
            artist =  state1.data,
            selectedArtist =  state2.artist_user_id,
            onSelectedArtist =  viewModel2::setArtistId,
            onSubmitClick =  viewModel2::postCommission,
            isLoading =  state2.isLoading,
            isSuccess =  state2.isSuccess,
            error = state2.error,
            onNavigateUp = onNavigateUp)
}

@Composable
fun HireArtistContent(
        price : String ,
        onPriceChange : (String) -> Unit ,
        address : String ,
        onAddressChange : (String) -> Unit ,
        description : String ,
        onDescriptionChange : (String) -> Unit ,
        artist : Artist ,
        selectedArtist : Int ,
        onSelectedArtist : (Int) -> Unit ,
        onSubmitClick : (Int) -> Unit ,
        isLoading : Boolean ,
        isSuccess : String?,
        error : String?,
        onNavigateUp : () -> Unit)
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
        BackToArtist(Modifier.align(Alignment.Start), onBackClick = onNavigateUp)

        TopMessageHire()

        HireArtistForm(
                price =  price,
                onPriceChange =  onPriceChange,
                address =  address,
                onAddressChange =  onAddressChange,
                description =  description,
                onDescriptionChange =  onDescriptionChange,
                artist =  artist,
                selectedArtist =  selectedArtist,
                onSelectedArtist =  onSelectedArtist,
                onSubmitClick = onSubmitClick)
    }
}

@Composable
fun HireArtistForm(
        price : String,
        onPriceChange : (String) -> Unit,
        address : String,
        onAddressChange : (String) -> Unit,
        description : String,
        onDescriptionChange : (String) -> Unit,
        artist : Artist,
        selectedArtist : Int,
        onSelectedArtist : (Int) -> Unit,
        onSubmitClick : (Int) -> Unit)
{
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf{
        price.isNotBlank() && address.isNotBlank() && description.isNotBlank() }

    Column(modifier = Modifier
            .fillMaxSize())
    {
        /**[Artist]*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , top = 20.dp , bottom = 16.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Artist Info",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 20.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Artist name: ${artist.name.orEmpty()}",
                     style = MaterialTheme.typography.caption,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Email: ${artist.email.orEmpty()}",
                     style = MaterialTheme.typography.caption,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Number: ${artist.number?:"No mobile number provided"}",
                     style = MaterialTheme.typography.caption,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
        }
        /**[Offer]*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Best Offer Price",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                OutlinedTextField(value = price ,
                                  onValueChange = onPriceChange ,
                                  modifier = Modifier.fillMaxWidth(),
                                  label = { Text(text = "Enter price in PHP (₱)") } ,
                                  leadingIcon = { Icon(Icons.Outlined.PriceChange , "") } ,
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
                                  singleLine = true)
            }
        }
        /**[Address]*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Delivery Address",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                OutlinedTextField(value = address ,
                                  onValueChange = onAddressChange ,
                                  modifier = Modifier.fillMaxWidth(),
                                  label = { Text(text = "Enter your address") } ,
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
                                  maxLines = 5)
            }
        }
        /**Description*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 20.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Request Description",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                OutlinedTextField(value = description ,
                                  onValueChange = onDescriptionChange ,
                                  modifier = Modifier.fillMaxWidth(),
                                  label = { Text(text = "Enter description for your request") } ,
                                  leadingIcon = { Icon(Icons.Outlined.NoteAlt , "") } ,
                                  textStyle = TextStyle(
                                          color = MaterialTheme.colors.onPrimary ,
                                          fontSize = 16.sp) ,
                                  shape = RoundedCornerShape(10.dp) ,
                                  keyboardOptions = KeyboardOptions(
                                          keyboardType = KeyboardType.Text ,
                                          imeAction = ImeAction.Done) ,
                                  keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })  ,
                                  maxLines = 10)
            }
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

                Button(onClick = { onSubmitClick(artist.id!!) } ,
                       enabled = isValidate ,
                       modifier = Modifier
                               .fillMaxWidth()
                               .height(50.dp) ,
                       colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) ,
                       shape = RoundedCornerShape(10.dp))
                {
                    Text(text = "Submit Request" , color = Color.White , style = typography.h6)
                }
            }
        }
    }
}

@Composable
fun BackToArtist(modifier : Modifier , onBackClick : () -> Unit)
{
    IconButton(onClick = onBackClick,
               modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 20.dp))
    {
        Icon(Icons.Outlined.ArrowBackIos , contentDescription = "" ,
             tint = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun TopMessageHire()
{
    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) ,
           horizontalAlignment = Alignment.CenterHorizontally ,
           verticalArrangement = Arrangement.Center)
    {
        Text(text = "Hire Artist" ,
             style = MaterialTheme.typography.h5 ,
             fontSize = 25.sp ,
             textAlign = TextAlign.Center)
    }
}