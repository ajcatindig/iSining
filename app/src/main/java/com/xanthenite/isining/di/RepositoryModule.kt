package com.xanthenite.isining.di

import com.xanthenite.isining.core.repository.AuthRepository
import com.xanthenite.isining.repository.AuthRepositoryImpl
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

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteRepository