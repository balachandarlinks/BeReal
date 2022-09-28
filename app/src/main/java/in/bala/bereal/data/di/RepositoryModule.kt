package `in`.bala.bereal.data.di

import `in`.bala.bereal.data.repo.auth.LocalAuthRepository
import `in`.bala.bereal.data.repo.directory.RetrofitDirectoryItemsRepository
import `in`.bala.bereal.data.repo.profile.RetrofitProfileRepository
import `in`.bala.bereal.domain.repo.auth.AuthRepository
import `in`.bala.bereal.domain.repo.directory.DirectoryItemsRepository
import `in`.bala.bereal.domain.repo.profile.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideProfileRepository(
        retrofitProfileRepository: RetrofitProfileRepository
    ): ProfileRepository

    @Binds
    abstract fun provideDirectoryItemsRepository(
        retrofitDirectoryItemsRepository: RetrofitDirectoryItemsRepository
    ): DirectoryItemsRepository

    @Binds
    abstract fun provideAuthRepository(
        localAuthRepository: LocalAuthRepository
    ): AuthRepository
}
