package com.example.crmedumobile.di

import com.example.crmedumobile.domain.repository.auth.AuthRepository
<<<<<<< HEAD
import com.example.crmedumobile.presentation.usecase.LoginUseCase
=======
import com.example.crmedumobile.domain.repository.notification.NotificationRepository
import com.example.crmedumobile.domain.repository.schedule.ScheduleRepository
import com.example.crmedumobile.domain.repository.user.UserRepository
import com.example.crmedumobile.domain.usecase.ChangeNotifyModeUseCase
import com.example.crmedumobile.domain.usecase.GetNotificationsUseCase
import com.example.crmedumobile.domain.usecase.GetProfileUseCase
import com.example.crmedumobile.domain.usecase.GetScheduleUseCase
import com.example.crmedumobile.domain.usecase.LogOutUseCase
import com.example.crmedumobile.domain.usecase.LoginUseCase
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
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
<<<<<<< HEAD
=======

    @Provides
    fun provideLogOutUseCase(
        authRepository: AuthRepository
    ): LogOutUseCase = LogOutUseCase(authRepository)

    @Provides
    fun provideProfileUseCase(
        userRepository: UserRepository
    ): GetProfileUseCase = GetProfileUseCase(userRepository)

    @Provides
    fun changeNotifyModeUseCase(
        userRepository: UserRepository
    ): ChangeNotifyModeUseCase = ChangeNotifyModeUseCase(userRepository)

    @Provides
    fun getScheduleUseCase(scheduleRepository: ScheduleRepository): GetScheduleUseCase =
        GetScheduleUseCase(scheduleRepository)

    @Provides
    fun getNotificationsUseCase(notificationRepository: NotificationRepository): GetNotificationsUseCase =
        GetNotificationsUseCase(notificationRepository)
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
}