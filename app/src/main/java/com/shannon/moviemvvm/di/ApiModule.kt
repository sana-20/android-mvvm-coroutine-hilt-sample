package com.shannon.moviemvvm.di

import com.example.data.remote.TheMovieDBInterface
import com.google.gson.GsonBuilder
import com.example.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun createHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(60, TimeUnit.SECONDS)
        client.readTimeout(60, TimeUnit.SECONDS)
        client.writeTimeout(60, TimeUnit.SECONDS)
        return client.addInterceptor {
            val url = it.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", Constants.API_KEY)
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()
            return@addInterceptor it.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TheMovieDBInterface {
        return retrofit.create(TheMovieDBInterface::class.java)
    }

}