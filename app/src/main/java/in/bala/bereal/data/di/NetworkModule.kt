package `in`.bala.bereal.data.di

import `in`.bala.bereal.BuildConfig
import `in`.bala.bereal.data.repo.directory.DirectoryItemsService
import `in`.bala.bereal.data.repo.profile.ProfileService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides @Named("auth_credentials")
    fun provideAuthCredentials(): String {
        return Credentials.basic("noel", "foobar")
    }

    @Provides
    fun provideOkHttpClient(
        @Named("auth_credentials") authCredentials: String,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", authCredentials)
                    .build()
                it.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .build()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    @Provides
    fun provideDirectoryItemsService(retrofit: Retrofit): DirectoryItemsService {
        return retrofit.create(DirectoryItemsService::class.java)
    }

    companion object {
        private const val BASE_URL = "http://163.172.147.216:8080"
        private const val NETWORK_TIMEOUT_DURATION_IN_SECONDS = 15L
    }
}
