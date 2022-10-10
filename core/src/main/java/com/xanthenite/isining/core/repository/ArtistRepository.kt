package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.Artist
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * Repository for Artists.
 */
@Singleton
interface ArtistRepository
{
    /**
     * Returns an artist
     *
     * @param artistId An artist ID.
     */
    fun getArtistById(id : Int) : Flow<Artist>

    /**
     * Returns all artist.
     */
    fun getAllArtists() : Flow<Either<List<Artist>>>
}