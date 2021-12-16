package com.intalalab.wsnmonitoring.di

import android.content.Context
import android.content.SharedPreferences
import com.intalalab.wsnmonitoring.BuildConfig
import com.intalalab.wsnmonitoring.data.remote.service.*
import com.intalalab.wsnmonitoring.data.remote.util.ErrorHandlerInterceptor
import com.intalalab.wsnmonitoring.data.remote.util.NullResponseConverter
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
    private const val TIMEOUT: Long = 30

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    @Singleton
    fun provideErrorHandlerInterceptor(@ApplicationContext context: Context): ErrorHandlerInterceptor =
        ErrorHandlerInterceptor(context)

    @Provides
    @Singleton
    fun provideOkHttp(
        cache: Cache,
        errorHandlerInterceptor: ErrorHandlerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(errorHandlerInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpCache(@ApplicationContext context: Context): Cache = Cache(
        context.cacheDir,
        CLIENT_CACHE_SIZE
    )

    @Provides
    @Singleton
    fun provideLoginApi(client: Lazy<OkHttpClient>): LoginApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_LOGIN)
            .callFactory { client.get().newCall(it) }
            .addConverterFactory(NullResponseConverter())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideWsnApi(client: Lazy<OkHttpClient>): WSNApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_WSN)
            .callFactory { client.get().newCall(it) }
            .addConverterFactory(NullResponseConverter())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WSNApi::class.java)

    @Provides
    @Singleton
    fun provideCoordinatorApi(client: Lazy<OkHttpClient>): CoordinatorApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_COORDINATOR)
            .callFactory { client.get().newCall(it) }
            .addConverterFactory(NullResponseConverter())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoordinatorApi::class.java)

    @Provides
    @Singleton
    fun provideRouterApi(client: Lazy<OkHttpClient>): RouterApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_ROUTER)
            .callFactory { client.get().newCall(it) }
            .addConverterFactory(NullResponseConverter())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RouterApi::class.java)

    @Provides
    @Singleton
    fun provideSensorApi(client: Lazy<OkHttpClient>): SensorApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_SENSOR)
            .callFactory { client.get().newCall(it) }
            .addConverterFactory(NullResponseConverter())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SensorApi::class.java)

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

}