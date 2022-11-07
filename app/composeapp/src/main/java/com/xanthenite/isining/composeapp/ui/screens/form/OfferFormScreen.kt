package com.xanthenite.isining.composeapp.ui.screens.form

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.composeapp.R
import com.xanthenite.isining.composeapp.component.dialog.FailureDialog
import com.xanthenite.isining.composeapp.component.dialog.LoaderDialog
import com.xanthenite.isining.composeapp.component.dialog.SuccessDialog
import com.xanthenite.isining.composeapp.ui.theme.typography
import com.xanthenite.isining.composeapp.utils.ISiningPreview
import com.xanthenite.isining.composeapp.utils.collectState
import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.core.model.PaymentChannel
import com.xanthenite.isining.view.viewmodel.detail.ArtworkDetailViewModel
import com.xanthenite.isining.view.viewmodel.form.OfferFormViewModel
import com.xanthenite.isining.view.viewmodel.form.PaymentChannelViewModel

@Composable
fun OfferFormScreen(
        onNavigateUp : () -> Unit,
        viewModel1 : ArtworkDetailViewModel,
        viewModel2 : PaymentChannelViewModel,
        viewModel3 : OfferFormViewModel)
{
    val state1 by viewModel1.collectState()
    val state2 by viewModel2.collectState()
    val state3 by viewModel3.collectState()

    OfferContent(
            isLoading = state3.isLoading ,
            price =  state3.price ,
            onPriceChange =  viewModel3::setPrice ,
            address =  state3.address ,
            onAddressChange =  viewModel3::setAddress ,
            note =  state3.note ,
            onNoteChange =  viewModel3::setNote ,
            selectedChannel =  state3.payment_channel_id ,
            onOptionSelected =  viewModel3::setPaymentChannelId ,
            onSubmitClick = viewModel3::postOffer ,
            onNavigateUp = onNavigateUp ,
            isSuccess =  state3.isSuccess ,
            error =  state3.error ,
            paymentChannel =  state2.data ,
            artwork = state1.data ,
            selectedArtwork = state3.artwork_id ,
            onArtworkSelected = viewModel3::setArtworkId,
            isValidAddress = state3.isValidAddress ?: true)
}

@Composable
fun OfferContent(
        isLoading : Boolean ,
        price : String? ,
        onPriceChange : (String) -> Unit ,
        address : String ,
        onAddressChange : (String) -> Unit ,
        note : String? ,
        onNoteChange : (String) -> Unit ,
        selectedChannel : Int ,
        onOptionSelected : (Int) -> Unit ,
        onSubmitClick : (Int) -> Unit ,
        onNavigateUp : () -> Unit ,
        isSuccess : String? ,
        error : String? ,
        paymentChannel : List<PaymentChannel> ,
        artwork : Artwork ,
        selectedArtwork : Int,
        onArtworkSelected : (Int) -> Unit,
        isValidAddress : Boolean)
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
        BackToArtwork(Modifier.align(Alignment.Start), onBackClick = onNavigateUp)

        TopMessageOffer()

        OfferForm(
                price =  price,
                onPriceChange =  onPriceChange,
                address =  address,
                onAddressChange =  onAddressChange,
                note =  note,
                onNoteChange =  onNoteChange,
                onSubmitClick = onSubmitClick ,
                paymentChannel = paymentChannel ,
                selectedChannel =  selectedChannel,
                onOptionSelected =  onOptionSelected,
                artwork = artwork,
                selectedArtwork = selectedArtwork,
                onArtworkSelected = onArtworkSelected,
                isValidAddress = isValidAddress)
    }

}

@Composable
fun OfferForm(
        price : String?,
        onPriceChange : (String) -> Unit,
        address : String,
        onAddressChange : (String) -> Unit,
        note : String?,
        onNoteChange : (String) -> Unit,
        onSubmitClick : (Int) -> Unit,
        paymentChannel : List<PaymentChannel>,
        selectedChannel : Int,
        onOptionSelected : (Int) -> Unit,
        artwork : Artwork,
        selectedArtwork : Int,
        onArtworkSelected : (Int) -> Unit,
        isValidAddress : Boolean)
{
    val helperText = ""
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf { address.isNotBlank() && selectedChannel != 0}


    Column(modifier = Modifier
            .fillMaxSize())
    {
        /**[Artwork]*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , top = 20.dp , bottom = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Artwork Info",
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
                Text(text = "Title: ${artwork.title.orEmpty()}",
                     style = MaterialTheme.typography.caption,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start,
                     maxLines = 2,
                     overflow = TextOverflow.Ellipsis)
            }
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Artist: ${artwork.user_name.orEmpty()}",
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
                Text(text = "Price: ₱${artwork.price.orEmpty()}",
                     style = MaterialTheme.typography.caption,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
        }
        /**[Modes]*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Mode of Payment",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            paymentChannel.forEach { index ->
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp)
                        .selectable(selected = (selectedChannel == index.id) , onClick = { onOptionSelected(index.id) } , role = Role.RadioButton),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                   )
                {
                    RadioButton(
                            selected = (selectedChannel == index.id) ,
                            onClick = { onOptionSelected(index.id) },
                            modifier = Modifier.padding(end = 8.dp),
                            colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colors.onPrimary,
                                    unselectedColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f)
                                                               )
                               )
                    Text(text = index.name,
                         style = MaterialTheme.typography.caption,
                         fontSize = 16.sp,
                         textAlign = TextAlign.Start)
                }
            }

        }
        /**[offer]*/
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp , end = 16.dp , bottom = 16.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(text = "Offer Price (optional)",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                OutlinedTextField(value = price.orEmpty() ,
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
               horizontalAlignment = Alignment.Start,
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
                              maxLines = 5,
                              isError = !isValidAddress)
            if (helperText.isEmpty()) {
                Spacer(modifier = Modifier.padding(1.dp))
                Text(
                        stringResource(id = R.string.message_address_invalid) ,
                        style = MaterialTheme.typography.caption , fontStyle = FontStyle.Italic , textAlign = TextAlign.Start)
            }
        }
        /**[Note]*/
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
                Text(text = "Additional Note (optional)",
                     style = MaterialTheme.typography.subtitle1,
                     fontSize = 18.sp,
                     textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                OutlinedTextField(value = note.orEmpty() ,
                                  onValueChange = onNoteChange ,
                                  modifier = Modifier.fillMaxWidth(),
                                  label = { Text(text = "Enter additional note for your offer") } ,
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
                    .fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center ,
                verticalAlignment = Alignment.CenterVertically)
            {
                Button(onClick = { onSubmitClick(artwork.id!!) } ,
                       enabled = isValidate ,
                       modifier = Modifier
                               .fillMaxSize()
                               .height(50.dp) ,
                       colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) ,
                       shape = RoundedCornerShape(10.dp))
                {
                    Text(text = "Submit Offer" , color = Color.White , style = typography.h6)
                }
            }
        }
    }
}

@Composable
fun BackToArtwork(modifier : Modifier, onBackClick : () -> Unit)
{
    IconButton(onClick = onBackClick, modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 20.dp))
    {
        Icon(Icons.Outlined.ArrowBackIos , contentDescription = "",
             tint = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun TopMessageOffer()
{
    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center)
    {
        Text(text = "Make an Offer",
             style = MaterialTheme.typography.h5,
             fontSize = 25.sp,
             textAlign = TextAlign.Center)
    }
}

//@Preview
//@Composable
//fun OfferFormPreview() = ISiningPreview {
//    OfferForm(price =  "1000.00" ,
//              onPriceChange =  {} ,
//              address =  "Sample Address" ,
//              onAddressChange =  {} ,
//              note =  "Sample Note" ,
//              onNoteChange = {} ,
//              onSubmitClick = {} ,
//              onBackClick = {} ,
//              paymentChannel = listOf(
//                      PaymentChannel(1 , "Online Payment") ,
//                      PaymentChannel(2, "Cash on Delivery")
//                                     ) ,
//              selectedChannel = 1,
//              artwork = ,
//              onOptionSelected = {})
//}

