package com.xanthenite.isining.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesContextModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineContext = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineContext = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineContext = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineContext = Dispatchers.Main.immediate

    @EmptyContext
    @Provides
    fun providesEmptyContext(): CoroutineContext = EmptyCoroutineContext
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class EmptyContext