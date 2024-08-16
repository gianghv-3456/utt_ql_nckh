package com.gianghv.android.di

import com.gianghv.android.repository.ProjectRepository
import com.gianghv.android.repository.ProjectRepositoryImpl
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
}
