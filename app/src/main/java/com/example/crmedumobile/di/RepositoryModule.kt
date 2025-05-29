package com.example.crmedumobile.di

import android.content.SharedPreferences
import com.example.crmedumobile.data.network.repository.AuthRepositoryImpl
import com.example.crmedumobile.data.network.repository.ScheduleRepositoryImpl
import com.example.crmedumobile.data.network.repository.UserRepositoryImpl
import com.example.crmedumobile.data.network.service.AuthService
import com.example.crmedumobile.data.network.service.ScheduleService
import com.example.crmedumobile.data.network.service.UserService
import com.example.crmedumobile.data.network.util.ErrorParser
import com.example.crmedumobile.domain.repository.auth.AuthRepository
import com.example.crmedumobile.domain.repository.schedule.ScheduleRepository
import com.example.crmedumobile.domain.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
        authService: AuthService,
        errorParser: ErrorParser,
        sharedPreferences: SharedPreferences
    ): AuthRepository =
        AuthRepositoryImpl(authService, errorParser, sharedPreferences)

    @Provides
    fun provideUserRepository(userService: UserService): UserRepository =
        UserRepositoryImpl(userService)

    @Provides
    fun provideScheduleRepository(
        scheduleService: ScheduleService,
        errorParser: ErrorParser
    ): ScheduleRepository = ScheduleRepositoryImpl(scheduleService, errorParser)
}
