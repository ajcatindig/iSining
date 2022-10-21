package com.xanthenite.isining.composeapp.ui.screens.form

import androidx.compose.runtime.Composable
import com.xanthenite.isining.composeapp.component.scaffold.ISiningScaffold
import com.xanthenite.isining.core.model.Artwork

@Composable
fun OfferFormScreen()
{

}

@Composable
fun OfferContent(
        data : Artwork,
        isLoading : Boolean,
        isConnectivityAvailable : Boolean?,
        error : String? ,
        onNavigateUp : () -> Unit ,
        onRefresh : () -> Unit,
        onSubmitClick : () -> Unit ,
        isSuccess : String?,
        onOfferChange : (String) -> Unit ,
        onAddressChange : (String) -> Unit ,
        onNoteChange : (String) -> Unit ,
        offerPrice : String?,
        address : String?,
        note : String?,)
{

}