package com.example.crmedumobile.di

<<<<<<< HEAD
import com.example.crmedumobile.data.network.service.AuthService
=======
import android.content.Context
import android.content.SharedPreferences
import com.example.crmedumobile.data.network.service.AuthService
import com.example.crmedumobile.data.network.service.NotificationService
import com.example.crmedumobile.data.network.service.ScheduleService
import com.example.crmedumobile.data.network.service.UserService
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
<<<<<<< HEAD
import dagger.hilt.components.SingletonComponent
=======
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://10.0.2.2:8080/api/v1/"

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .build()

    @Provides
<<<<<<< HEAD
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
=======
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val token = sharedPreferences.getString("jwt_token", null)
                val requestBuilder = original.newBuilder()
                if (!token.isNullOrEmpty()) {
                    requestBuilder.header("Authorization", "Bearer $token")
                }
                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
<<<<<<< HEAD
=======


    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    fun provideScheduleService(retrofit: Retrofit): ScheduleService =
        retrofit.create(ScheduleService::class.java)

    @Provides
    fun provideNotificationService(retrofit: Retrofit): NotificationService =
        retrofit.create(NotificationService::class.java)
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
}
