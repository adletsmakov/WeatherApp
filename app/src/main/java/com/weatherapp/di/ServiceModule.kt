package com.weatherapp.di

import com.weatherapp.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(WeatherApi.BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun weatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(
        WeatherApi::class.java);

    @Provides
    @Singleton
    fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}