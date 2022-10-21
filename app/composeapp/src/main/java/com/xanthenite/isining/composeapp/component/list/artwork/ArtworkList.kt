package com.xanthenite.isining.composeapp.component.list.artwork

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.composeapp.component.cards.artwork.ArtworkCard
import com.xanthenite.isining.composeapp.component.cards.artwork.ArtworkRowCard
import com.xanthenite.isining.core.model.Artwork

@Composable
fun ArtworkList(data : List<Artwork?> , onClick : (Artwork) -> Unit)
{
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        modifier = Modifier.testTag("artworksList"))
    {
        items(
            items = data,
            itemContent = { index ->
                ArtworkCard(
                    imageUrl = index?.pictures?.first(),
                    title =  index?.title.orEmpty(),
                    artistName = index?.user_name.orEmpty(),
                    onArtworkClick = { onClick(index!!) }
                )
            },
            key = { Triple(it?.id, it?.title, it?.user_name) }
        )
    }
}

@Composable
fun ArtworkLazyRow(data : List<Artwork?>, onClick : (Artwork) -> Unit)
{
    LazyRow(
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
        modifier = Modifier
                .fillMaxWidth()
                .testTag("artworksLazyRow"))
    {
        items(
            items = data,
            itemContent = { index ->
                ArtworkRowCard(
                    imageUrl =  index?.pictures?.first(),
                    title =  index?.title.orEmpty(),
                    artistName = index?.user_name.orEmpty(),
                    onArtworkClick = { onClick(index!!) })
            },
            key = { Triple(it?.id, it?.title, it?.user_name) }
        )
    }
}