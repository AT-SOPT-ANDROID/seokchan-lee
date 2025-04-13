package org.sopt.at.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.at.database.database.LocalUserDataSourceImpl
import org.sopt.at.database.source.LocalUserDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalUserDataSource(
        @ApplicationContext context: Context
    ): LocalUserDataSource {
        return LocalUserDataSourceImpl(context)
    }
}
