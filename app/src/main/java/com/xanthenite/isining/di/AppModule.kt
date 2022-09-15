package com.xanthenite.isining.di

import android.app.Application
import androidx.work.WorkManager
import com.xanthenite.isining.connectivity.ConnectivityObserverImpl
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.preference.PreferenceManager
import com.xanthenite.isining.core.session.SessionManager
import com.xanthenite.isining.preference.PreferenceManagerImpl
import com.xanthenite.isining.preference.uiModePrefDataStore
import com.xanthenite.isining.session.SessionManagerImpl
import com.xanthenite.isining.session.SharedPreferencesFactory
import com.xanthenite.isining.utils.connectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule
{

    @Singleton
    @Provides
    fun providePreferenceManager(application : Application) : PreferenceManager {
        return PreferenceManagerImpl(application.uiModePrefDataStore)
    }

    @Singleton
    @Provides
    fun provideSessionManager(application: Application): SessionManager
    {
        return SessionManagerImpl(SharedPreferencesFactory.sessionPreferences(application))
    }

    @Singleton
    @Provides
    fun provideConnectivityObserver(application: Application): ConnectivityObserver
    {
        return ConnectivityObserverImpl(application.connectivityManager)
    }

    @Singleton
    @Provides
    fun provideWorkManager(application: Application): WorkManager
    {
        return WorkManager.getInstance(application)
    }

}