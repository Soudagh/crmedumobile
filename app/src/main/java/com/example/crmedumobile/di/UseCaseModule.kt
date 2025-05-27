package com.example.crmedumobile.di

import com.example.crmedumobile.domain.repository.auth.AuthRepository
import com.example.crmedumobile.domain.repository.user.UserRepository
import com.example.crmedumobile.domain.usecase.LoginUseCase
import com.example.crmedumobile.domain.usecase.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase = LoginUseCase(authRepository)

    @Provides
    fun provideProfileUseCase(
        userRepository: UserRepository
    ): ProfileUseCase = ProfileUseCase(userRepository)
}