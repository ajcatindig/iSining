package com.xanthenite.isining.repository

import android.util.Log
import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.core.repository.ArtworkRepository
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.data.remote.api.ArtworkService
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Source of data of artworks from network
 */
@Singleton
class ArtworkRepositoryImpl @Inject internal constructor(
        private val artworkService : ArtworkService
):ArtworkRepository
{
    override fun getArtworkById(id : Int) : Flow<Artwork> = flow {
        val artworkResponse = artworkService.getArtworkById(id).getResponse()

        when(artworkResponse.state) {
            State.SUCCESS -> Either.success(emit(artworkResponse.data))
            else -> Either.error(artworkResponse.message!!)
        }
    }.catch {
        Log.e("ArtworkRepositoryImpl", "catch ${it.message!!}")
    }

    override fun getAllArtworks() : Flow<Either<List<Artwork>>> = flow {
        val artworksResponse = artworkService.getAllArtworks().getResponse()

        val state = when(artworksResponse.state) {
            State.SUCCESS -> Either.success(artworksResponse.data)
            else -> Either.error(artworksResponse.message!!)
        }
        emit(state)
        Log.d("Data", "${artworksResponse.data}")
    }.catch {
        Log.e("ArtworkRepositoryImpl", "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred"))
    }

}