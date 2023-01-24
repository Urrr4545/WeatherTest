package com.urrr4545.weathertest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.urrr4545.weathertest.Constants
import com.urrr4545.weathertest.data.WeatherApi
import com.urrr4545.weathertest.data.response.WeatherRepositoryImpl
import com.urrr4545.weathertest.domain.mapper.DataMapper
import com.urrr4545.weathertest.domain.repository.WeatherRepository
import com.urrr4545.weathertest.domain.usecase.GetWeatherUseCase
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
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideWeatherApiService(retrofitBuilder: Retrofit.Builder): WeatherApi {
        return retrofitBuilder
            .build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDataMapper(): DataMapper {
        return DataMapper()
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        api: WeatherApi,
        dataMapper: DataMapper
    ) = WeatherRepositoryImpl(api, dataMapper) as WeatherRepository

    @Singleton
    @Provides
    fun provideGetWeatherUseCase(
        weatherRepository: WeatherRepository,
        dataMapper: DataMapper
    ): GetWeatherUseCase {
        return GetWeatherUseCase(weatherRepository, dataMapper)
    }
}
