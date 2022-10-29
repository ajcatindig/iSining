package com.xanthenite.isining.composeapp.component.list.transaction

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.composeapp.component.cards.transaction.TransactionCard
import com.xanthenite.isining.core.model.Transaction

@Composable
fun TransactionList(data : List<Transaction>)
{
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp) ,
        modifier = Modifier.testTag("transactionsList"))
    {
        items(
            items = data,
            itemContent = { index ->
                TransactionCard(
                        title = index.title,
                        price =  index.price.toString(),
                        address =  index.address,
                        description =  index.description,
                        verified_at = index.verified_at,
                        dateCreated = index.created_at)
            },
            key = { Triple(it.id, it.title, it.verified_at)}
        )
    }
}