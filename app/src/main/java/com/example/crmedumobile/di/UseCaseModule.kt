package com.example.crmedumobile.di

import com.example.crmedumobile.domain.repository.attendance.AttendanceRepository
import com.example.crmedumobile.domain.repository.auth.AuthRepository
import com.example.crmedumobile.domain.repository.lesson.LessonRepository
import com.example.crmedumobile.domain.repository.notification.NotificationRepository
import com.example.crmedumobile.domain.repository.schedule.ScheduleRepository
import com.example.crmedumobile.domain.repository.user.UserRepository
import com.example.crmedumobile.domain.usecase.ChangeNotifyModeUseCase
import com.example.crmedumobile.domain.usecase.GetLessonUseCase
import com.example.crmedumobile.domain.usecase.GetNotificationsUseCase
import com.example.crmedumobile.domain.usecase.GetProfileUseCase
import com.example.crmedumobile.domain.usecase.GetScheduleUseCase
import com.example.crmedumobile.domain.usecase.GetUserRoleUseCase
import com.example.crmedumobile.domain.usecase.LogOutUseCase
import com.example.crmedumobile.domain.usecase.LoginUseCase
import com.example.crmedumobile.domain.usecase.SetAttendanceUseCase
import com.example.crmedumobile.domain.usecase.SetLinkUseCase
import com.example.crmedumobile.domain.usecase.SetNotesUseCase
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

    @Provides
    fun getLessonUseCase(lessonRepository: LessonRepository): GetLessonUseCase = GetLessonUseCase(lessonRepository)

    @Provides
    fun setAttendanceUseCase(attendanceRepository: AttendanceRepository): SetAttendanceUseCase = SetAttendanceUseCase(attendanceRepository)

    @Provides
    fun setLinkUseCase(lessonRepository: LessonRepository): SetLinkUseCase = SetLinkUseCase(lessonRepository)

    @Provides
    fun setNotesUseCase(lessonRepository: LessonRepository): SetNotesUseCase = SetNotesUseCase(lessonRepository)
}