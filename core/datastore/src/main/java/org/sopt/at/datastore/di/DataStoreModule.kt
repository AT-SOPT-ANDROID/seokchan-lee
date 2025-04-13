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
import org.sopt.at.datastore.source.AutoSignInPreferencesDataSource
import org.sopt.at.datastore.datastore.DefaultAutoSignInPreferencesDataSource
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindsAutoSignInLocalDataSource(
        dataSource: DefaultAutoSignInPreferencesDataSource,
    ): AutoSignInPreferencesDataSource
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
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(AUTH_PREFERENCES)
    }

    private const val AUTH_PREFERENCES = "org.sopt.at.auth_preferences"
}