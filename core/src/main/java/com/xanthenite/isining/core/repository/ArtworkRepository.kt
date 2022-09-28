package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.Artwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * Repository for Artworks.
 */
@Singleton
interface ArtworkRepository
{
    /**
     * Returns an exhibit
     *
     * @param artworkId An artwork ID.
     */
    fun getArtworkById(id : Int) : Flow<Artwork>

    /**
     * Returns all artworks.
     */
    fun getAllArtworks() : Flow<Either<List<Artwork>>>
}