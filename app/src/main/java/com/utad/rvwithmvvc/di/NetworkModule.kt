package com.utad.rvwithmvvc.di

import com.utad.rvwithmvvc.data.network.MovieApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // informar la respuesta HTTP
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                },
            )
    }.build()

    // Connexion de retrofit a la pagina web
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constantes.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):MovieApiClient{
        return retrofit.create(MovieApiClient::class.java)
    }


}