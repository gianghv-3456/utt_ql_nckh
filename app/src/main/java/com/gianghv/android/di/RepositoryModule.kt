package com.gianghv.android.di

import com.gianghv.android.repository.ProjectRepository
import com.gianghv.android.repository.ProjectRepositoryImpl
import com.gianghv.android.repository.UserRepository
import com.gianghv.android.repository.UserRepositoryImpl
import com.gianghv.android.repository.source.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRoomRepository(): ProjectRepository {
        return ProjectRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository {
        return UserRepositoryImpl(userDataSource)
    }
}
