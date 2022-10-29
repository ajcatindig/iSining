package com.xanthenite.isining.composeapp.component.list.transaction

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.composeapp.component.cards.transaction.OfferCard
import com.xanthenite.isining.core.model.Offer

@Composable
fun OfferList(data : List<Offer>)
{
    LazyColumn(
            contentPadding = PaddingValues(vertical = 4.dp) ,
            modifier = Modifier.testTag("offersList"))
    {
        items(
             items = data,
             itemContent = { index ->
                 OfferCard(
                         title = index.title,
                         price = index.price.toString(),
                         address = index.address,
                         note = index.note,
                         accepted_at = index.accepted_at,
                         deleted_at = index.deleted_at,
                         dateCreated = index.created_at ?: "Not available"
                 )
            }
        )
    }
}