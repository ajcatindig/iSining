package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.Exhibit
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

/**
 * Repository for Exhibits.
 */
@Singleton
interface ExhibitRepository
{
    /**
     * Returns an exhibit
     *
     * @param exhibitId An exhibit ID.
     */
    fun getExhibitById(id : Int) : Flow<Exhibit>

    /**
     * Returns all current exhibits.
     */
    fun getCurrentExhibits() : Flow<Either<List<Exhibit>>>

    /**
     * Returns all upcoming exhibits.
     */
    fun getUpcomingExhibits() : Flow<Either<List<Exhibit>>>

    /**
     * Returns all past exhibits.
     */
    fun getPastExhibits() : Flow<Either<List<Exhibit>>>
}