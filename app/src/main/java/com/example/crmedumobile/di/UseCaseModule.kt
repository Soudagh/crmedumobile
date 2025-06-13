package com.example.crmedumobile.di

import com.example.crmedumobile.domain.repository.auth.AuthRepository
import com.example.crmedumobile.domain.repository.notification.NotificationRepository
import com.example.crmedumobile.domain.repository.schedule.ScheduleRepository
import com.example.crmedumobile.domain.repository.user.UserRepository
import com.example.crmedumobile.domain.usecase.ChangeNotifyModeUseCase
import com.example.crmedumobile.domain.usecase.GetNotificationsUseCase
import com.example.crmedumobile.domain.usecase.GetProfileUseCase
import com.example.crmedumobile.domain.usecase.GetScheduleUseCase
import com.example.crmedumobile.domain.usecase.GetUserRoleUseCase
import com.example.crmedumobile.domain.usecase.LogOutUseCase
import com.example.crmedumobile.domain.usecase.LoginUseCase
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

    @Provides
    fun getRoleUseCase(authRepository: AuthRepository): GetUserRoleUseCase =
        GetUserRoleUseCase(authRepository)
}