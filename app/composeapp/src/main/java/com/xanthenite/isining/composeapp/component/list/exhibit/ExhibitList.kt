package com.xanthenite.isining.composeapp.component.list.exhibit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.composeapp.component.cards.artwork.ArtworkRowCard
import com.xanthenite.isining.composeapp.component.cards.exhibit.ExhibitCard
import com.xanthenite.isining.core.model.Exhibit

@Composable
fun ExhibitList(data : List<Exhibit>, onClick : (Exhibit) -> Unit)
{
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        modifier = Modifier.testTag("exhibitsList"))
    {
        items(
            items = data,
            itemContent = { index ->
                ExhibitCard(
                        imageUrl = index.cover_path ,
                        title = index.title !! ,
                        startDate = index.start_date !! ,
                        endDate = index.end_date !! ,
                        onExhibitClick = { onClick(index) })
            },
            key = { Triple(it.id, it.title, it.description)}
        )
    }
}

@Composable
fun CurrentExhibitRow(data : List<Exhibit> , onClick : (Exhibit) -> Unit)
{
    LazyRow(
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            modifier = Modifier
                    .fillMaxWidth()
                    .testTag("CurrentExhibitRow"))
    {
        items(
            items = data,
            itemContent = { index ->
                ExhibitCard(
                        imageUrl = index.cover_path ,
                        title = index.title !! ,
                        startDate = index.start_date !! ,
                        endDate = index.end_date !! ,
                        onExhibitClick = { onClick(index) })
            },
            key = { Triple(it.id, it.title, it.description)}
        )
    }
}

@Composable
fun UpcomingExhibitRow(data : List<Exhibit>)
{
    LazyRow(
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            modifier = Modifier
                    .fillMaxWidth()
                    .testTag("UpcomingExhibitRow"))
    {
        items(
            items = data,
            itemContent = { index ->
                ExhibitCard(
                        imageUrl = index.cover_path ,
                        title = index.title !! ,
                        startDate = index.start_date !! ,
                        endDate = index.end_date !! ,
                        onExhibitClick = { /*NO-OP needed*/ })
            },
            key = { Triple(it.id, it.title, it.description)}
        )
    }
}

@Composable
fun PastExhibitRow(data : List<Exhibit> , onClick : (Exhibit) -> Unit)
{
    LazyRow(
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            modifier = Modifier
                    .fillMaxWidth()
                    .testTag("PastExhibitRow"))
    {
        items(
            items = data,
            itemContent = { index ->
                ExhibitCard(
                        imageUrl = index.cover_path ,
                        title = index.title !! ,
                        startDate = index.start_date !! ,
                        endDate = index.end_date !! ,
                        onExhibitClick = { onClick(index) })
            },
            key = { Triple(it.id, it.title, it.description)}
        )
    }
}
