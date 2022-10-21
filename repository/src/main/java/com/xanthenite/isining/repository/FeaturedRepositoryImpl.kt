package com.xanthenite.isining.repository

import android.util.Log
import com.xanthenite.isining.core.model.Featured
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.core.repository.FeaturedRepository
import com.xanthenite.isining.data.remote.api.FeaturedService
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeaturedRepositoryImpl @Inject internal constructor(
        private val featuredService : FeaturedService
) : FeaturedRepository
{
    override fun getCurrentFeatured() : Flow<Either<Featured>> = flow {
        val featuredResponse = featuredService.getCurrentFeatured().getResponse()

        val state = when (featuredResponse.state) {
            State.SUCCESS -> Either.success(featuredResponse.data)
            else -> Either.error(featuredResponse.message!!)
        }
        emit(state)
        Log.d("Data", "${featuredResponse.data}")
    }.catch {
        Log.e("FeaturedRepositoryImpl", "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred"))
    }
}