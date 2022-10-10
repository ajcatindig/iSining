package com.xanthenite.isining.composeapp.component.list.artist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.xanthenite.isining.composeapp.component.cards.artist.ArtistCard
import com.xanthenite.isining.core.model.Artist

@Composable
fun ArtistList(data : List<Artist>, onClick : (Artist) -> Unit)
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp))
    {
        items(
            items = data,
            itemContent = { index ->
                ArtistCard(
                    imageUrl = index.profile_photo_path ,
                    artistName = index.name,
                    onArtistClick = { onClick(index) }
                )
            },
            key = { Triple(it.id, it.name, it.email) }
        )
    }
}