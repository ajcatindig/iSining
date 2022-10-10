package com.xanthenite.isining.repository

import android.util.Log
import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.core.repository.ArtistRepository
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.data.remote.api.ArtistService
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Source of data of artists from network
 */
@Singleton
class ArtistRepositoryImpl @Inject internal constructor(
        private val artistService : ArtistService
) : ArtistRepository
{
    override fun getArtistById(id : Int) : Flow<Artist>
    {
        TODO("Not yet implemented")
    }

    override fun getAllArtists() : Flow<Either<List<Artist>>> = flow {
        val artistResponse = artistService.getAllArtists().getResponse()

        val state = when(artistResponse.state) {
            State.SUCCESS -> Either.success(artistResponse.data)
            else -> Either.error(artistResponse.message!!)
        }
        emit(state)
        Log.d("Data", "${artistResponse.data}")
    }.catch {
        Log.e("ArtistRepositoryImpl", "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred"))
    }
}