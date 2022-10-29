package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.Featured
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * Repository for Current Featured Artist and Artwork.
 */
@Singleton
interface FeaturedRepository
{
    /**
     * Returns current featured artist and artwork.
     */
    fun getCurrentFeatured() : Flow<Either<Featured?>>
}