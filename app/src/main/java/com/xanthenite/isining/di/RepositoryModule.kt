package com.xanthenite.isining.di

import com.xanthenite.isining.core.repository.*
import com.xanthenite.isining.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule
{

    @Binds
    fun iSiningAuthRepository(iSiningAuthRepositoryImpl : AuthRepositoryImpl) : AuthRepository

    @Binds
    @RemoteRepository
    fun iSiningExhibitRepository(iSiningExhibitRepository : ExhibitRepositoryImpl) : ExhibitRepository

    @Binds
    @RemoteRepository
    fun iSiningArtworkRepository(iSiningArtworkRepository : ArtworkRepositoryImpl) : ArtworkRepository

    @Binds
    @RemoteRepository
    fun iSiningArtistRepository(iSiningArtistRepository : ArtistRepositoryImpl) : ArtistRepository

    @Binds
    @RemoteRepository
    fun iSiningUserRepository(iSiningUserRepository : UserRepositoryImpl) : UserRepository

    @Binds
    @RemoteRepository
    fun iSiningFeaturedRepository(iSIningFeaturedRepository : FeaturedRepositoryImpl) : FeaturedRepository

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteRepository