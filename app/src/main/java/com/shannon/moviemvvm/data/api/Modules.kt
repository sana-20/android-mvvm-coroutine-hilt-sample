package com.shannon.moviemvvm.data.api

import com.google.gson.GsonBuilder
import com.shannon.moviemvvm.utils.Constants.API_KEY
import com.shannon.moviemvvm.utils.Constants.BASE_URL
import com.shannon.moviemvvm.data.repository.Repository
import com.shannon.moviemvvm.data.repository.RepositoryImpl
import com.shannon.moviemvvm.ui.movies.MainActivityViewModel
import com.shannon.moviemvvm.ui.details.SingleMovieViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModules = module {
    single {
        createWebService<TheMovieDBInterface>(okHttpClient = createHttpClient(), baseUrl = BASE_URL)
    }

    factory<Repository> {
        RepositoryImpl(
            apiService = get()
        )
    }
}


val viewModelModules = module {
    viewModel { MainActivityViewModel(repository = get()) }
    viewModel { SingleMovieViewModel(repository = get()) }
}


fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.connectTimeout(60, TimeUnit.SECONDS)
    client.readTimeout(60, TimeUnit.SECONDS)
    client.writeTimeout(60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val url = it.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = it.request()
            .newBuilder()
            .url(url)
            .build()
        return@addInterceptor it.proceed(request)
    }.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}