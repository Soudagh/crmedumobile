package com.example.crmedumobile.di

import com.example.crmedumobile.data.network.util.ErrorParser
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun provideErrorParser(moshi: Moshi): ErrorParser = ErrorParser(moshi)
}
