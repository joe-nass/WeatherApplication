package com.example.core.di

import com.example.weatherapplication.BuildConfig
import com.example.weatherapplication.data.data_sources.remote.ForecastApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val API_KEY = "key"
        val DAYS_PARAM = "days"

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val urlWithKey = request.url.newBuilder()
                    .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                    .build()

                val daysParam = urlWithKey.queryParameter(DAYS_PARAM)?.toInt()

                require(daysParam in 1..7) {
                    "Query parameter 'days' must be between 1 and 7, but was $daysParam"
                }

                val newRequest = request.newBuilder()
                    .url(urlWithKey)
                    .build()

                chain.proceed(newRequest)
            }
//            .addInterceptor(loggingInterceptor)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideForecastApi(retrofit: Retrofit): ForecastApiService {
        return retrofit.create(ForecastApiService::class.java)
    }
}