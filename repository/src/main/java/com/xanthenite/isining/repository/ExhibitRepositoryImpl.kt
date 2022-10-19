package com.xanthenite.isining.repository

import android.util.Log
import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.core.repository.ExhibitRepository
import com.xanthenite.isining.data.remote.api.ExhibitService
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Source of data of exhibits from network
 */
@Singleton
class ExhibitRepositoryImpl @Inject internal constructor(
    private val exhibitService : ExhibitService
) : ExhibitRepository
{
    override fun getExhibitById(id : Int) : Flow<Exhibit> = flow {
        val exhibitResponse = exhibitService.getExhibitById(id).getResponse()

        when (exhibitResponse.state) {
            State.SUCCESS -> Either.success(emit(exhibitResponse.data))
            else -> Either.error(exhibitResponse.message!!)
        }
    }.catch {
        Log.e("ExhibitRepositoryImpl", "catch ${it.message!!}")
    }

    override fun getCurrentExhibits() : Flow<Either<List<Exhibit>>> = flow {
        val exhibitsResponse = exhibitService.getCurrentExhibits().getResponse()

        val state = when(exhibitsResponse.state) {
            State.SUCCESS -> Either.success(exhibitsResponse.data)
            else -> Either.error(exhibitsResponse.message!!)
        }
        emit(state)
        Log.d("Current Data", "${exhibitsResponse.data}")
     }.catch {
        Log.e("ExhibitRepositoryImpl", "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred")) }

    override fun getUpcomingExhibits() : Flow<Either<List<Exhibit>>> = flow {
        val exhibitResponse = exhibitService.getUpcomingExhibits().getResponse()

        val state = when(exhibitResponse.state) {
            State.SUCCESS -> Either.success(exhibitResponse.data)
            else -> Either.error(exhibitResponse.message!!)
        }
        emit(state)
        Log.d("Upcoming Data", "${exhibitResponse.data}")
    }.catch {
        Log.e("ExhibitRepositoryImpl", "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred"))
    }

    override fun getPastExhibits() : Flow<Either<List<Exhibit>>> = flow {
        val exhibitResponse = exhibitService.getPastExhibits().getResponse()

        val state = when(exhibitResponse.state) {
            State.SUCCESS -> Either.success(exhibitResponse.data)
            else -> Either.error(exhibitResponse.message!!)
    }
        emit(state)
        Log.d("Past Data", "${exhibitResponse.data}")
    }.catch {
        Log.e("ExhibitRepositoryImpl", "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred"))
}
}