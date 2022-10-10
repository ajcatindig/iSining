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
    fun getExhibitById(id : Int) : Flow<Either<Exhibit>>

    /**
     * Returns all exhibits.
     */
    fun getAllExhibits() : Flow<Either<List<Exhibit>>>
}