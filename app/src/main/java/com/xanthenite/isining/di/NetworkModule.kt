package com.xanthenite.isining.di

import com.xanthenite.isining.core.utils.moshi
import com.xanthenite.isining.data.remote.Constant
import com.xanthenite.isining.data.remote.api.*
import com.xanthenite.isining.data.remote.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule
{
    private val baseRetrofitBuilder : Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())

    private val okHttpClientBuilder : OkHttpClient.Builder =
            OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)


    @Provides
    fun provideAuthService() : AuthService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.build())
            .build()
            .create(AuthService::class.java)
    }

    @Provides
    fun provideExhibitService(authInterceptor : AuthInterceptor) : ExhibitService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(ExhibitService::class.java)
    }

    @Provides
    fun provideArtworkService(authInterceptor : AuthInterceptor) : ArtworkService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(ArtworkService::class.java)
    }

    @Provides
    fun provideArtistService(authInterceptor : AuthInterceptor) : ArtistService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(ArtistService::class.java)
    }

    @Provides
    fun provideUserService(authInterceptor : AuthInterceptor) : UserService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(UserService::class.java)
    }

    @Provides
    fun provideFeaturedService(authInterceptor : AuthInterceptor) : FeaturedService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(FeaturedService::class.java)
    }

    @Provides
    fun provideTransactionService(authInterceptor : AuthInterceptor) : TransactionService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(TransactionService::class.java)
    }
}