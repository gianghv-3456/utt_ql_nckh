package com.gianghv.android.di

import android.content.Context
import android.content.SharedPreferences
import com.gianghv.android.repository.api.FirebaseApi
import com.gianghv.android.repository.api.FirebaseApiImpl
import com.gianghv.android.repository.source.UserDataSource
import com.gianghv.android.repository.source.UserDataSourceImpl
import com.gianghv.android.util.app.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideFirebaseApi(): FirebaseApi {
        return FirebaseApiImpl()
    }

    @Provides
    @Singleton
    fun provideUserDataSource(api: FirebaseApi): UserDataSource {
        return UserDataSourceImpl(api)
    }
}
