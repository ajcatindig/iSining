package com.xanthenite.isining.composeapp.component.list.transaction

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.composeapp.component.cards.transaction.CommissionCard
import com.xanthenite.isining.core.model.Commission
import java.math.RoundingMode

@Composable
fun CommissionList(data : List<Commission>)
{
    LazyColumn(
            contentPadding = PaddingValues(vertical = 4.dp) ,
            modifier = Modifier.testTag("offersList"))
    {
        items(
            items = data,
            itemContent = { index ->
                CommissionCard(
                        title = index.artist_name,
                        price = index.price.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toString(),
                        address = index.address,
                        note = index.description,
                        accepted_at = index.accepted_at,
                        deleted_at = index.deleted_at,
                        dateCreated = index.created_at ?: "Not available"
                )
            }
        )
    }
}