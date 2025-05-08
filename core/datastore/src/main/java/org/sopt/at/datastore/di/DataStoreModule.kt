package org.sopt.at.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.at.datastore.datastore.DefaultAutoSignInPreferencesDataSource
import org.sopt.at.datastore.datastore.DefaultUserTokenPreferencesDataSource
import org.sopt.at.datastore.source.AutoSignInPreferencesDataSource
import org.sopt.at.datastore.source.UserTokenPreferencesDataSource
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindsAutoSignInLocalDataSource(
        dataSource: DefaultAutoSignInPreferencesDataSource
    ): AutoSignInPreferencesDataSource

    @Binds
    abstract fun bindsUserTokenLocalDataSource(
        dataSource: DefaultUserTokenPreferencesDataSource
    ): UserTokenPreferencesDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    fun Context.createDataStore(preferencesName: String): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(this, preferencesName)),
            produceFile = { this.preferencesDataStoreFile(preferencesName) }
        )
    }

    @Singleton
    @Provides
    @Named("authDataStore")
    fun provideAuthDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(AUTH_PREFERENCES)
    }

    @Singleton
    @Provides
    @Named("tokenDataStore")
    fun provideTokenDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(TOKEN_PREFERENCES)
    }

    private const val AUTH_PREFERENCES = "org.sopt.at.auth_preferences"
    private const val TOKEN_PREFERENCES = "org.sopt.at.token_preferences"
}
