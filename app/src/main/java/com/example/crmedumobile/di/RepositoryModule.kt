package com.example.crmedumobile.di

import com.example.crmedumobile.data.network.repository.AuthRepositoryImpl
import com.example.crmedumobile.data.network.service.AuthService
import com.example.crmedumobile.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(authService: AuthService): AuthRepository =
        AuthRepositoryImpl(authService)
}
