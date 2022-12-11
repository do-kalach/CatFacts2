package com.example.catfacts.hilt.service

import com.example.catfacts.service.WebService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {
        private val BASE_URL = "https://catfact.ninja"

        @Singleton
        @Provides
        fun providesWebservice(moshi: Moshi): WebService {
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
                .create(WebService::class.java)
        }
}